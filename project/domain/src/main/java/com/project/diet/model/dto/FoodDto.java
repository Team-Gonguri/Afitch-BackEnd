package com.project.diet.model.dto;

import com.project.diet.model.entity.Food;
import com.project.diet.model.entity.Ingredient;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FoodDto {

    private Long id;

    private String name;

    private String foodCategory;

    private int size;

    private String unit;

    private double protein;

    private double fat;

    private double carbohydrate;


    private double calories = 0;

    public FoodDto(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.unit = food.getUnit();
        this.size = food.getSize();
        this.foodCategory = food.getFoodCategory();
        this.protein = food.getIngredients().getProtein();
        this.fat = food.getIngredients().getFat();
        this.carbohydrate = food.getIngredients().getCarbohydrate();
    }

    public Ingredient getIngredient() {
        return new Ingredient(this.protein, this.fat, this.carbohydrate, this.calories);
    }
}
