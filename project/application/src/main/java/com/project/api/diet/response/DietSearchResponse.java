package com.project.api.diet.response;

import com.project.diet.model.dto.SimpleMealDto;
import com.project.diet.model.entity.Ingredient;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
@ApiModel("식단 검색 응답")
public class DietSearchResponse{

    @ApiModelProperty("식사 리스트 (아침,점심,저녁)")
    private List<SimpleMealDto> diet;

    @ApiModelProperty("식단 총 영양분")
    private Ingredient ingredients;

    public DietSearchResponse(List<SimpleMealDto> diet) {
        this.diet = diet;
        this.ingredients = calculate();
    }

    private Ingredient calculate() {
        Ingredient ingredient = new Ingredient();
        diet.forEach(
                meal -> {
                    Ingredient mealIngredients = meal.getIngredient();
                    ingredient.setCarbohydrate(ingredient.getCarbohydrate() + mealIngredients.getCarbohydrate());
                    ingredient.setFat(ingredient.getFat() + mealIngredients.getFat());
                    ingredient.setProtein(ingredient.getProtein() + mealIngredients.getProtein());
                    ingredient.setCalories(ingredient.getCalories() + mealIngredients.getCalories());
                }
        );
        return ingredient;
    }
}
