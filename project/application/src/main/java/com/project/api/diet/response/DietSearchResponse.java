package com.project.api.diet.response;

import com.project.diet.model.dto.SimpleMealDto;
import com.project.diet.model.entity.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class DietSearchResponse {
    private List<SimpleMealDto> diet;
    private Ingredient ingredients;

    public DietSearchResponse(List<SimpleMealDto> diet) {
        this.diet = diet;
        this.ingredients = calculate();
    }

    private Ingredient calculate() {
        Ingredient ingredient = new Ingredient();
        diet.forEach(
                dto -> {
                    Ingredient it = dto.getIngredient();
                    ingredient.setCalcium(ingredient.getCalcium() + it.getCalcium());
                    ingredient.setCarbohydrate(ingredient.getCarbohydrate() + it.getCarbohydrate());
                    ingredient.setDietary_fiber(ingredient.getDietary_fiber() + it.getDietary_fiber());
                    ingredient.setFat(ingredient.getFat() + it.getFat());
                    ingredient.setProtein(ingredient.getProtein() + it.getProtein());
                    ingredient.setSalt(ingredient.getSalt() + it.getSalt());
                }
        );
        return ingredient;
    }
}
