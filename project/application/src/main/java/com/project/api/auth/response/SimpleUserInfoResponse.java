package com.project.api.auth.response;

import com.project.auth.model.dto.UserInfoDto;
import com.project.exercise.model.dto.SimpleExerciseParticipationDto;

import java.util.List;

public class SimpleUserInfoResponse {
    private String nickName;
    private double weight;
    private double height;

    public SimpleUserInfoResponse(UserInfoDto userInfoDto) {
        nickName = userInfoDto.getNickName();
        weight = userInfoDto.getWeight();
        height = userInfoDto.getHeight();
    }
}
