package com.project.api.auth.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.checkerframework.checker.regex.qual.Regex;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@ApiModel("로그인 요청")
public class SignInRequest {
    @NotEmpty
    @ApiModelProperty("계정ID")
    private String accountId;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\\\d)[!-~₩]{8,16}$", message = "영문,숫자,특수문자가 포함되어야 하며, 8~16자 이내여야합니다.")
    @ApiModelProperty("비밀번호(8~16, 특수문자 영어 숫자 최소하나씩")
    private String password;
}
