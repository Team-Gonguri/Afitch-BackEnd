package com.project.api.diet.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.DateUtils;
import com.project.diet.model.dto.FoodWrapperDto;
import com.project.diet.model.dto.MealDto;
import com.project.diet.model.entity.Food;
import com.project.diet.model.entity.FoodWrapper;
import com.project.diet.model.entity.Ingredient;
import com.project.diet.model.entity.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class MealDetailResponse {
    private Long id;
    private MealType type;
    private List<FoodWrapperDto> foods;

    private Ingredient ingredients;

    private String createdAt;

    public MealDetailResponse(MealDto mealDto) {
        this.id = mealDto.getId();
        this.type = mealDto.getType();
        this.foods = mealDto.getFoods();
        this.createdAt = mealDto.getCreatedAt();
        this.ingredients = calculate(mealDto.getFoods());

    }

    private Ingredient calculate(List<FoodWrapperDto> foodWrappers) {
        Ingredient ingredient = new Ingredient();
        foodWrappers.forEach(
                wrappers -> {
                    Ingredient it = wrappers.getFood().getIngredients();
                    ingredient.setCalcium(ingredient.getCalcium() + it.getCalcium() * wrappers.getSize());
                    ingredient.setCarbohydrate(ingredient.getCarbohydrate() + it.getCarbohydrate() * wrappers.getSize());
                    ingredient.setDietary_fiber(ingredient.getDietary_fiber() + it.getDietary_fiber() * wrappers.getSize());
                    ingredient.setFat(ingredient.getFat() + it.getFat() * wrappers.getSize());
                    ingredient.setProtein(ingredient.getProtein() + it.getProtein() * wrappers.getSize());
                    ingredient.setSalt(ingredient.getSalt() + it.getSalt() * wrappers.getSize());
                }
        );
        return ingredient;
    }

}
