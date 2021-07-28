package com.project.api.diet.controller;

import com.project.api.diet.response.FoodCategoryResponse;
import com.project.diet.service.DietService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/diet")
@RequiredArgsConstructor
public class DietController {

    private final DietService dietService;

    @GetMapping("/categories")
    @ApiOperation("음식 카테고리 가져오기")
    @ResponseStatus(HttpStatus.OK)
    public FoodCategoryResponse getFoodCategories() {
        return new FoodCategoryResponse(dietService.getFoodCategories());
    }

}
