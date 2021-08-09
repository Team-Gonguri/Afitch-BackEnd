package com.project.api.auth.response;

import com.project.auth.model.dto.UserInfoDto;
import com.project.exercise.model.dto.SimpleExerciseParticipationDto;
import lombok.Getter;

import java.util.List;

@Getter
public class DetailUserInfoResponse {
    private String nickName;
    private double weight;
    private double height;
    private List<SimpleExerciseParticipationDto> myParticipation;

    public DetailUserInfoResponse(UserInfoDto userInfoDto, List<SimpleExerciseParticipationDto> myParticipation) {
        nickName = userInfoDto.getNickName();
        weight = userInfoDto.getWeight();
        height = userInfoDto.getHeight();
        this.myParticipation = myParticipation;
    }
}
