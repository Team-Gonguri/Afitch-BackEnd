package com.project.api.diet.response;

import com.project.diet.model.dto.SimpleMealDto;
import com.project.diet.model.entity.Ingredient;
import com.project.diet.model.entity.enums.MealType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Getter
@AllArgsConstructor
@ApiModel("식단 검색 응답")
public class DietSearchResponse {

    @ApiModelProperty("식사 리스트 (아침,점심,저녁,간식)")
    private Map<MealType, SimpleMealDto> diet;

    @ApiModelProperty("식단 총 영양분")
    private Ingredient ingredients;

    public DietSearchResponse(List<SimpleMealDto> diets) {
        this.diet = initMap(diets);
        this.ingredients = calculate();
    }

    private Map<MealType, SimpleMealDto> initMap(List<SimpleMealDto> meals) {
        Map<MealType, SimpleMealDto> diet = new LinkedHashMap<>();
        for (MealType type : MealType.values())
            diet.put(type, null);
        meals.forEach(meal -> diet.put(meal.getType(), meal));
        return diet;
    }

    private Ingredient calculate() {
        Ingredient ingredient = new Ingredient();
        diet.values().forEach(
                meal -> {
                    if(meal != null){
                        Ingredient mealIngredients = meal.getIngredient();
                        ingredient.setCarbohydrate(ingredient.getCarbohydrate() + mealIngredients.getCarbohydrate());
                        ingredient.setFat(ingredient.getFat() + mealIngredients.getFat());
                        ingredient.setProtein(ingredient.getProtein() + mealIngredients.getProtein());
                        ingredient.setCalories(ingredient.getCalories() + mealIngredients.getCalories());
                    }
                }
        );
        return ingredient;
    }
}
