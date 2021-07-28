package com.project.api.diet.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@ApiModel("음식 분류 카테고리")
public class FoodCategoryResponse {
    private List<String> foodCategories;
}
