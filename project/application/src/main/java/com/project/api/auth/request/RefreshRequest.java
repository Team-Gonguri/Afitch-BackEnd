package com.project.api.auth.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("AccessToken 갱신 요청")
public class RefreshRequest {
    @NotEmpty
    @ApiModelProperty("RefreshToken")
    private String refreshToken;
}
