package com.project.api.exercise.response;

import com.project.exercise.model.entity.dto.ExpertExerciseDto;
import com.project.exercise.model.entity.enums.ExerciseType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel("트레이너 운동영상 가져오기 응답")
public class GetExpertVideoResponse {

    @ApiModelProperty("운동 이름")
    private String name;
    @ApiModelProperty("운동 부위")
    private ExerciseType type;
    @ApiModelProperty("트레이너 운동 영상 URL")
    private String expertURL;

    public GetExpertVideoResponse(ExpertExerciseDto dto){
        this.name = dto.getExerciseName();
        this.type = dto.getType();
        this.expertURL = dto.getExpertURL();
    }
}
