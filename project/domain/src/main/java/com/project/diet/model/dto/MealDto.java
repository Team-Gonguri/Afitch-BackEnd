package com.project.diet.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.DateUtils;
import com.project.diet.model.entity.Meal;
import com.project.diet.model.entity.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MealDto {
    private Long id;
    private MealType type;
    private List<FoodWrapperDto> foods;
    private String createdAt;

    public MealDto(Meal meal, List<FoodWrapperDto> foodWrappers) {
        this.id = meal.getId();
        this.type = meal.getType();
        this.foods = foodWrappers;
        this.createdAt = DateUtils.parseDateToString(meal.getCreatedAt());
    }
}
