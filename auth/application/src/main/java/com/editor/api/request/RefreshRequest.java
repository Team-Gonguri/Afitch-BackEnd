package com.editor.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@ApiModel("AccessToken 갱신 요청")
public class RefreshRequest {
    @NotEmpty
    @ApiModelProperty("RefreshToken")
    private String refreshToken;
}
