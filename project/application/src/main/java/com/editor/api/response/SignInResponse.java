package com.editor.api.response;

import com.editor.model.dto.TokenDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel("로그인 응답")
public class SignInResponse {
    @ApiModelProperty("AccessToken")
    private String accessToken;
    @ApiModelProperty("RefreshToken")
    private String refreshToken;

    public SignInResponse(TokenDto tokenDto) {
        this.accessToken = tokenDto.getAccessToken();
        this.refreshToken = tokenDto.getRefreshToken();
    }
}
