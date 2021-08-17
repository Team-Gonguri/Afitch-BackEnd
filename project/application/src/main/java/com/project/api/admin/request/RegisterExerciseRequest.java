package com.project.api.admin.request;

import com.project.exercise.model.entity.enums.ExerciseCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("운동 저장 요청")
public class RegisterExerciseRequest {

    @NotEmpty
    @ApiModelProperty("운동 이름")
    private String name;

    @NotNull
    @ApiModelProperty("운동 부위")
    private ExerciseCategory category;
}
