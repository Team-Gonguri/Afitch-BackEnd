package com.project.diet.model.dto;

import com.project.diet.model.entity.FoodWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FoodWrapperDto {
    private int size;
    private FoodDto food;

    public FoodWrapperDto(FoodWrapper foodWrapper) {
        this.size = foodWrapper.getSize();
        this.food = new FoodDto(foodWrapper.getFood());
    }
}
