package com.editor.api.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@ApiModel("회원가입 요청")
public class SignUpRequest {
    @NotEmpty
    @ApiModelProperty("계정 ID")
    private String accountId;

    @NotEmpty
    @ApiModelProperty("비밀번호")
    private String password;

    @Email
    @ApiModelProperty("이메일")
    private String email;
}
