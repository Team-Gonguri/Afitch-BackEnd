package com.project.api.exercise.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@ApiModel("댓글 저장, 수정 요청")
@AllArgsConstructor
public class ExerciseCommentRequest {
    @NotEmpty
    @ApiModelProperty("댓글")
    private String text;
}
