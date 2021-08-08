package com.project.api.admin.response;

import com.project.exercise.model.dto.DetailExerciseDto;
import com.project.exercise.model.entity.enums.ExerciseCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel("운동 저장 응답")
public class RegisterExerciseResponse {
    @ApiModelProperty("운동 식별자")
    private Long id;

    @ApiModelProperty("운동 이름")
    private String name;

    @ApiModelProperty("운동 카테고리")
    private ExerciseCategory category;

    @ApiModelProperty("트레이너 운동영상 URL")
    private String url;



    public RegisterExerciseResponse(DetailExerciseDto dto){
        this.id = dto.getId();
        this.category = dto.getCategory();
        this.name = dto.getName();
        this.url = dto.getUrl();
    }
}
