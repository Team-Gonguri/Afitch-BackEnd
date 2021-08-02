package com.project.auth.model.dto;

import com.project.auth.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoDto {
    private String nickName;
    private double height;
    private double weight;

    public UserInfoDto(User user){
        this.nickName = user.getNickName();
        this.height = user.getHeight();
        this.weight = user.getWeight();
    }
}
