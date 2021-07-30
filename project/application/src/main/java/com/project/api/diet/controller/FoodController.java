package com.project.api.diet.controller;

import com.project.api.diet.response.FoodCategoryResponse;
import com.project.api.diet.response.FoodSearchResponse;
import com.project.diet.service.FoodService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/categories")
    @ApiOperation("음식 카테고리 가져오기")
    @ResponseStatus(HttpStatus.OK)
    public FoodCategoryResponse getFoodCategories() {
        return new FoodCategoryResponse(foodService.getFoodCategories());
    }

    @GetMapping("/search")
    @ApiOperation("음식 검색하기")
    @ResponseStatus(HttpStatus.OK)
    public FoodSearchResponse findFood(
            @RequestParam String keyword,
            @RequestParam int page
    ) {
        return new FoodSearchResponse(foodService.findFoods(keyword, page));
    }
}
