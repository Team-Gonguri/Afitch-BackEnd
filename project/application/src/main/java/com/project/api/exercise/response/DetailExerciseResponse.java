package com.project.api.exercise.response;

import com.project.exercise.model.dto.DetailExerciseDto;
import com.project.exercise.model.entity.enums.ExerciseCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel("운동세부정보 가져오기 응답")
public class DetailExerciseResponse {

    @ApiModelProperty("운동 이름")
    private String name;
    @ApiModelProperty("운동 부위")
    private ExerciseCategory category;
    @ApiModelProperty("트레이너 운동 영상 URL")
    private String expertURL;

    public DetailExerciseResponse(DetailExerciseDto dto){
        this.name = dto.getName();
        this.category = dto.getCategory();
        this.expertURL = dto.getUrl();
    }
}
