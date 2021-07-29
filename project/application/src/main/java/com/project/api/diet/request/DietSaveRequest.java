package com.project.api.diet.request;

import com.project.diet.model.dto.FoodWrapperDto;
import com.project.diet.model.entity.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DietSaveRequest {
    private MealType type;
    private List<FoodWrapperDto> foods;
}
