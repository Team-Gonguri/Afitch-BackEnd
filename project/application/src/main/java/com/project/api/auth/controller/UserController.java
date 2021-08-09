package com.project.api.auth.controller;

import com.project.api.auth.request.UpdateUserInfoRequest;
import com.project.api.auth.response.DetailUserInfoResponse;
import com.project.api.auth.response.SimpleUserInfoResponse;
import com.project.auth.model.dto.UserInfoDto;
import com.project.auth.service.UserService;
import com.project.exercise.service.ExercisePartiipationService;
import com.project.security.AuthInfo;
import com.project.security.Authenticated;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ExercisePartiipationService exercisePartiipationService;

    @GetMapping
    @ApiOperation("내 정보 가져오기")
    public DetailUserInfoResponse getMyInfo(@Authenticated AuthInfo authInfo) throws ParseException {
        return new DetailUserInfoResponse(
                userService.getMyInfo(authInfo.getId()),
                exercisePartiipationService.getTodayTop4Participation(authInfo.getId())
        );
    }

    @PutMapping
    @ApiOperation("내 정보 수정")
    public SimpleUserInfoResponse updateMyInfo(
            @Authenticated AuthInfo authInfo,
            @Valid @RequestBody UpdateUserInfoRequest req) {

        UserInfoDto dto = new UserInfoDto(req.getNickName(),
                req.getHeight(),
                req.getWeight());
        return new SimpleUserInfoResponse(userService.updateUserInfo(
                authInfo.getId(),
                dto
        ));
    }
}
