package com.project.api.auth.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@ApiModel("회원가입 요청")
public class SignUpRequest {
    @NotEmpty
    @ApiModelProperty("계정 ID")
    private String accountId;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[!-~₩]{8,16}$", message = "영문,숫자,특수문자가 포함가능하며 , 8~16자 이내여야합니다.")
    @ApiModelProperty("비밀번호")
    private String password;

    @NotEmpty
    @ApiModelProperty("닉네임")
    private String nickName;

    @ApiModelProperty("관리자 회원가입용(유저의 경우 빈문자열)")
    private String adminCode;
}
