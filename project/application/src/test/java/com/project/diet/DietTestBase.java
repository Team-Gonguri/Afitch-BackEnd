package com.project.diet;

import com.project.utils.DateUtils;
import com.project.common.CommonTestBase;
import com.project.diet.model.dto.FoodWrapperDto;
import com.project.diet.model.dto.MealDto;
import com.project.diet.model.entity.Food;
import com.project.diet.model.entity.Ingredient;
import com.project.diet.model.entity.enums.MealType;
import com.project.diet.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DietTestBase extends CommonTestBase {

    @Autowired
    protected DietService dietService;

    protected MealDto createMeal(MealType type) throws ParseException {
        List<FoodWrapperDto> foodWrapperDto = getFoodWrapperDtoList();
        return dietService.saveMeal(9999L, foodWrapperDto, type, DateUtils.parseDateToString(new Date()));
    }

    protected List<FoodWrapperDto> getFoodWrapperDtoList() {
        return getFoods().stream().map(it -> new FoodWrapperDto(1, it)).collect(Collectors.toList());

    }

    private List<Food> getFoods() {
        return List.of(
                new Food(
                        1L,
                        "꿩불고기",
                        "구이류",
                        500,
                        "g",
                        new Ingredient(
                                33.5, 8.5, 39.7, 368.8
                        )
                ),
                new Food(
                        2L,
                        "닭갈비",
                        "구이류",
                        400,
                        "g",
                        new Ingredient(
                                45.9, 25.8, 44.9, 595.61
                        )
                ),
                new Food(
                        3L,
                        "더덕구이",
                        "구이류",
                        100,
                        "g",
                        new Ingredient(3.1, 5.2, 31.1, 184)
                )
        );
    }
}
