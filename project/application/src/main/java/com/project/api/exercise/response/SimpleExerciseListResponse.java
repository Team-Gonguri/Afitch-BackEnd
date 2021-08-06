package com.project.api.exercise.response;

import com.project.exercise.model.dto.SimpleExerciseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@ApiModel("카테고리별 간단한 운동정보 리스트")
public class SimpleExerciseListResponse {
    @ApiModelProperty("운동들")
    private List<SimpleExerciseDto> exercises;
}
