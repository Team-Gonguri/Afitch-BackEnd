package com.project.api.auth.controller;

import com.project.api.auth.request.UpdateUserInfoRequest;
import com.project.api.auth.response.UserInfoResponse;
import com.project.auth.model.dto.UserInfoDto;
import com.project.auth.service.UserService;
import com.project.security.AuthInfo;
import com.project.security.Authenticated;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @ApiOperation("내 정보 가져오기")
    public UserInfoResponse getMyInfo(@Authenticated AuthInfo authInfo) {
        return new UserInfoResponse(
                userService.getMyInfo(authInfo.getId())
        );
    }

    @PutMapping
    @ApiOperation("내 정보 수정")
    public UserInfoResponse updateMyInfo(
            @Authenticated AuthInfo authInfo,
            @Valid @RequestBody UpdateUserInfoRequest req) {

        UserInfoDto dto = new UserInfoDto(req.getNickName(),
                req.getHeight(),
                req.getWeight());
        return new UserInfoResponse(userService.updateUserInfo(
                authInfo.getId(),
                dto
        ));
    }
}
