package com.project.api.diet.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.DateUtils;
import com.project.diet.model.dto.FoodWrapperDto;
import com.project.diet.model.dto.MealDto;
import com.project.diet.model.entity.Food;
import com.project.diet.model.entity.FoodWrapper;
import com.project.diet.model.entity.Ingredient;
import com.project.diet.model.entity.enums.MealType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@ApiModel("식사 상세정보")
public class MealDetailResponse {
    @ApiModelProperty("식사 식별자")
    private Long id;
    @ApiModelProperty("식사 타입 (BREAKFAST | LUNCH | DINNER)")
    private MealType type;
    @ApiModelProperty("저장되어있는 음식들")
    private List<FoodWrapperDto> foods;
    @ApiModelProperty("식사 총 영양분")
    private Ingredient ingredients;
    @ApiModelProperty("날짜 (yyyyMMdd)")
    private String createdAt;

    public MealDetailResponse(MealDto mealDto) {
        this.id = mealDto.getId();
        this.type = mealDto.getType();
        this.foods = mealDto.getFoods();
        this.createdAt = mealDto.getCreatedAt();
        this.ingredients = calculate(mealDto.getFoods());

    }

    private Ingredient calculate(List<FoodWrapperDto> foodWrappers) {
        Ingredient ingredient = new Ingredient();
        foodWrappers.forEach(
                wrappers -> {
                    Ingredient it = wrappers.getFood().getIngredients();
                    ingredient.setCarbohydrate(ingredient.getCarbohydrate() + it.getCarbohydrate() * wrappers.getSize());
                    ingredient.setFat(ingredient.getFat() + it.getFat() * wrappers.getSize());
                    ingredient.setProtein(ingredient.getProtein() + it.getProtein() * wrappers.getSize());
                }
        );
        return ingredient;
    }

}
