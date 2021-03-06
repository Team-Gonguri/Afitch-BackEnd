package com.project.diet.service;

import com.project.auth.exceptions.UserNotExistsException;
import com.project.auth.model.entity.User;
import com.project.auth.model.repository.UserRepository;
import com.project.diet.exceptions.FoodNotExistException;
import com.project.diet.exceptions.MealNotExistsException;
import com.project.diet.model.dto.FoodWrapperDto;
import com.project.diet.model.dto.MealDto;
import com.project.diet.model.dto.SimpleMealDto;
import com.project.diet.model.entity.Food;
import com.project.diet.model.entity.FoodWrapper;
import com.project.diet.model.entity.Ingredient;
import com.project.diet.model.entity.Meal;
import com.project.diet.model.entity.enums.FoodType;
import com.project.diet.model.entity.enums.MealType;
import com.project.diet.model.repository.FoodRepository;
import com.project.diet.model.repository.FoodWrapperRepository;
import com.project.diet.model.repository.MealRepository;
import com.project.exception.NotYourContentsException;
import com.project.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DietService {
    private final FoodRepository foodRepository;
    private final FoodWrapperRepository foodWrapperRepository;
    private final MealRepository mealRepository;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public List<SimpleMealDto> getDiet(Long userId, String date) throws ParseException {
        User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        List<Meal> meals = mealRepository.findByUserAndCreatedAtOrderByTypeAsc(user, DateUtils.parseStringToDate(date));
        return meals.stream().map(meal -> new SimpleMealDto(
                meal,
                foodWrapperRepository.findAllByMeal(meal)
                        .stream().map(FoodWrapperDto::new).collect(Collectors.toList())
        )).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MealDto getMeal(Long userId, Long mealId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        Meal meal = mealRepository.findById(mealId).orElseThrow(MealNotExistsException::new);

        if (!meal.getUser().equals(user))
            throw new NotYourContentsException();
        List<FoodWrapperDto> foods = foodWrapperRepository.findAllByMeal(meal)
                .stream().map(FoodWrapperDto::new).collect(Collectors.toList());
        return new MealDto(meal, foods);
    }

    @Transactional
    public MealDto saveMeal(Long userId, List<FoodWrapperDto> dto, MealType type, String date) throws ParseException {
        User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        Meal meal = mealRepository.findByUserAndTypeAndCreatedAt(user, type, DateUtils.parseStringToDate(date));
        Ingredient totalIngredients = calMealIngredients(dto);
        if (meal == null)
            meal = mealRepository.save(new Meal(null, type, user, DateUtils.parseStringToDate(date), totalIngredients));
        else {
            List<FoodWrapper> foodWrappers = foodWrapperRepository.findAllByMeal(meal);
            List<Food> personalFood = foodWrappers.stream().map(FoodWrapper::getFood)
                    .filter(food -> food.getFoodType().equals(FoodType.PERSONAL)).collect(Collectors.toList());
            foodWrapperRepository.deleteAll(foodWrappers);
            foodRepository.deleteAll(personalFood);
        }

        saveFoodWrappers(meal, dto);
        return new MealDto(meal, foodWrapperRepository.findAllByMeal(meal).stream().map(FoodWrapperDto::new).collect(Collectors.toList()));
    }

    @Transactional
    void saveFoodWrappers(Meal meal, List<FoodWrapperDto> dto) {
        List<FoodWrapper> foods = dto.stream().map(
                wrapper -> {
                    Food food;

                    if (wrapper.getFood().getId() == null) {
                        food = foodRepository.save(
                                new Food(
                                        null,
                                        wrapper.getFood().getName(),
                                        wrapper.getFood().getSize(),
                                        wrapper.getFood().getUnit(),
                                        FoodType.PERSONAL,
                                        wrapper.getFood().parsingIngredient()
                                )
                        );
                    } else
                        food = foodRepository.findById(wrapper.getFood().getId()).orElseThrow(FoodNotExistException::new);

                    return new FoodWrapper(
                            food,
                            meal,
                            wrapper.getSize()
                    );
                }).collect(Collectors.toList());
        foodWrapperRepository.saveAll(foods);
    }

    private Ingredient calMealIngredients(List<FoodWrapperDto> dto) {
        Ingredient ingredient = new Ingredient();
        dto.forEach(wrapper -> {
            Ingredient foodIngredient = wrapper.getFood().parsingIngredient();
            ingredient.setCarbohydrate(ingredient.getCarbohydrate() + foodIngredient.getCarbohydrate() * wrapper.getSize());
            ingredient.setFat(ingredient.getFat() + foodIngredient.getFat() * wrapper.getSize());
            ingredient.setProtein(ingredient.getProtein() + foodIngredient.getFat() * wrapper.getSize());
            ingredient.setCalories(ingredient.getCalories() + foodIngredient.getCalories() * wrapper.getSize());
        });
        return ingredient;
    }


}
