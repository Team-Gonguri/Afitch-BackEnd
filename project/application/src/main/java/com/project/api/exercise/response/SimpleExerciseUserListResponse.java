package com.project.api.exercise.response;

import com.project.exercise.model.dto.SimpleExerciseParticipationDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@ApiModel("간단한 운동별 참여한 사용자 정보")
public class SimpleExerciseUserListResponse {
   @ApiModelProperty("정렬기준에 따라서 정렬된 사용자 정보")
    private List<SimpleExerciseParticipationDto> lists;
}
