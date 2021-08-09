package com.project.api.auth.response;

import com.project.auth.model.dto.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
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
