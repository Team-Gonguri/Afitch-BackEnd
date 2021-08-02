package com.project.api.auth.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserInfoRequest {
    private String nickName;
    private double height;
    private double weight;
}
