package com.project.api.auth.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel("Refresh요청 응답")
public class RefreshResponse {
    @ApiModelProperty("갱신된 AccessToken")
    private String accessToken;
}
