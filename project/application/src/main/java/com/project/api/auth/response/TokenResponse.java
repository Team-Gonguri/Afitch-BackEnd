package com.project.api.auth.response;

import com.project.auth.model.dto.TokenDto;
import com.project.security.JwtTokenDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel("로그인 응답")
public class TokenResponse {
    @ApiModelProperty("AccessToken")
    private JwtTokenDto access;
    @ApiModelProperty("RefreshToken")
    private JwtTokenDto refresh;

    public TokenResponse(TokenDto dto){
        this.access = dto.getAccess();
        this.refresh = dto.getRefresh();
    }
}
