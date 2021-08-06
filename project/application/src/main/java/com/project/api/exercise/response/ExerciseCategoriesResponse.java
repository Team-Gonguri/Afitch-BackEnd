package com.project.api.exercise.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@ApiModel("운동 카테고리 종류 가져오기 응답")
public class ExerciseCategoriesResponse {
    @ApiModelProperty("카테고리")
    private List<String> categories;
}
