package com.project.diet.model.dto;

import com.project.diet.model.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SimpleFoodDto {
    private Long id;

    private String name;

    public SimpleFoodDto(Food food) {
        this.id = food.getId();
        this.name = food.getName();

    }
}
