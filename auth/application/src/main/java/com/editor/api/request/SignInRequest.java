package com.editor.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@ApiModel("로그인 요청")
public class SignInRequest {
    @NotEmpty
    @ApiModelProperty("계정ID")
    private String accountId;

    @NotEmpty
    @ApiModelProperty("비밀번호")
    private String password;
}
