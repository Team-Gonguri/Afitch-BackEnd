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

    private int size;

    private String unit;

    private double protein;

    private double fat;

    private double carbohydrate;


    private double calories;

    public FoodDto(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.unit = food.getUnit();
        this.size = food.getSize();
        this.protein = food.getIngredients().getProtein();
        this.fat = food.getIngredients().getFat();
        this.carbohydrate = food.getIngredients().getCarbohydrate();
        this.calories = food.getIngredients().getCalories();
    }

    public Ingredient parsingIngredient() {
        return new Ingredient(this.protein, this.fat, this.carbohydrate, this.calories);
    }
}
