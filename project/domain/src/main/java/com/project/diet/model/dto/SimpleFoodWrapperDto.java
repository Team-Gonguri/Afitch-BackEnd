package com.project.diet.model.dto;

import com.project.diet.model.entity.FoodWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SimpleFoodWrapperDto {

    private int size;
    private SimpleFoodDto food;

    public SimpleFoodWrapperDto(FoodWrapper foodWrapper) {
        this.size = foodWrapper.getSize();
        this.food = new SimpleFoodDto(new FoodDto(foodWrapper.getFood()));
    }

    public SimpleFoodWrapperDto(FoodWrapperDto foodWrapperDto) {
        this.size = foodWrapperDto.getSize();
        this.food = new SimpleFoodDto(foodWrapperDto.getFood());
    }
}
