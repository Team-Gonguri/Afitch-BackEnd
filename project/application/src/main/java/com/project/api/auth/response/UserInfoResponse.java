package com.project.api.auth.response;

import com.project.auth.model.dto.UserInfoDto;
import lombok.Getter;

@Getter
public class UserInfoResponse {
    private String nickName;
    private double weight;
    private double height;

    public UserInfoResponse(UserInfoDto userInfoDto) {
        nickName = userInfoDto.getNickName();
        weight = userInfoDto.getWeight();
        height = userInfoDto.getHeight();
    }
}
