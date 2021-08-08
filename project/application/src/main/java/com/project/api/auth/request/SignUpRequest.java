package com.project.api.auth.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;

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

    @NotEmpty
    @ApiModelProperty("닉네임")
    private String nickName;

    @ApiModelProperty("관리자 회원가입용(유저의 경우 빈문자열)")
    private String adminCode;
}
