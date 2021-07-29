package com.project.diet.service;

import com.project.auth.exceptions.UserNotExistsException;
import com.project.auth.model.entity.User;
import com.project.auth.model.repository.UserRepository;
import com.project.common.dto.PageDto;
import com.project.common.utils.PageUtils;
import com.project.diet.model.dto.FoodWrapperDto;
import com.project.diet.model.dto.MealDto;
import com.project.diet.model.entity.Food;
import com.project.diet.model.entity.FoodWrapper;
import com.project.diet.model.entity.Meal;
import com.project.diet.model.entity.enums.MealType;
import com.project.diet.model.repository.FoodRepository;
import com.project.diet.model.repository.FoodWrapperRepository;
import com.project.diet.model.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DietService {
    private final FoodRepository foodRepository;
    private final FoodWrapperRepository foodWrapperRepository;
    private final MealRepository mealRepository;
    private final UserRepository userRepository;


    @Cacheable(cacheNames = "food_categories")
    @Transactional(readOnly = true)
    public List<String> getFoodCategories() {
        return foodRepository.findAllDistinctFoodCategories();
    }

    @Transactional(readOnly = true)
    public PageDto<Food> findFoods(String keyword, int page) {
        return new PageDto(foodRepository.findFoodByNameContaining(keyword, PageUtils.of(page)));
    }

    @Transactional
    public MealDto saveDiet(Long accountId, List<FoodWrapperDto> dto, MealType type) {
        User user = userRepository.findById(accountId).orElseThrow(UserNotExistsException::new);
        Meal meal = mealRepository.save(new Meal(null, type, user, new Date()));
        List<FoodWrapper> foods = dto.stream().map(
                wrapper -> new FoodWrapper(
                        wrapper.getFood(),
                        meal,
                        wrapper.getSize()
                )
        ).collect(Collectors.toList());
        foodWrapperRepository.saveAll(foods);
        return new MealDto(meal, dto);
    }
}
