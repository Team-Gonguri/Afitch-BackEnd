package com.project.diet.model.dto;

import com.project.DateUtils;
import com.project.diet.model.entity.Ingredient;
import com.project.diet.model.entity.Meal;
import com.project.diet.model.entity.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class SimpleMealDto {
    private Long id;
    private MealType type;
    private List<SimpleFoodWrapperDto> foods;
    private String createdAt;
    private Ingredient ingredient;

    public SimpleMealDto(Meal meal, List<FoodWrapperDto> foodWrappers) {
        this.id = meal.getId();
        this.type = meal.getType();
        this.foods = foodWrappers.stream().map(SimpleFoodWrapperDto::new).collect(Collectors.toList());
        this.createdAt = DateUtils.parseDateToString(meal.getCreatedAt());
        this.ingredient = meal.getIngredient();
    }
}
