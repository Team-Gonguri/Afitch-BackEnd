package com.project.api.exercise.response;

import com.project.exercise.model.entity.dto.UserExerciseDto;
import com.project.exercise.model.entity.enums.ExerciseType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel("사용자 운동영상 저장에 관한 응답")
public class SaveExerciseResponse {
    @ApiModelProperty("운동이름")
    private String name;
    @ApiModelProperty("사용자 운동 영상 URL")
    private String userURL;
    @ApiModelProperty("트레이너 운동 영상 URL")
    private String expertURL;

    public SaveExerciseResponse(UserExerciseDto dto) {
        this.name = dto.getExerciseName();
        this.userURL = dto.getUserURL();
        this.expertURL = dto.getExpertURL();
    }
}

