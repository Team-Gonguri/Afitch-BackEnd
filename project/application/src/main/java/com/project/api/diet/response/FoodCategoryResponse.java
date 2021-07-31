package com.project.api.diet.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@ApiModel("음식 분류 카테고리 응답 ")
public class FoodCategoryResponse {
    @ApiModelProperty("카테고리")
    private List<String> foodCategories;
}
