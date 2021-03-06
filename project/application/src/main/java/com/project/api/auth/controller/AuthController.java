package com.project.api.auth.controller;

import com.project.api.auth.request.RefreshRequest;
import com.project.api.auth.request.SignInRequest;
import com.project.api.auth.request.SignUpRequest;
import com.project.api.auth.response.RefreshResponse;
import com.project.api.auth.response.TokenResponse;
import com.project.auth.model.dto.SignInDto;
import com.project.auth.model.dto.SignUpDto;
import com.project.auth.service.AuthService;
import com.project.security.enums.UserRole;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("회원가입")
    public TokenResponse signUp(@RequestBody @Valid SignUpRequest req,
                                @RequestParam UserRole role) {
        return new TokenResponse(
                authService.signUp(
                new SignUpDto(req.getAccountId(),
                        req.getPassword(),
                        req.getNickName(),
                        role,
                        req.getAdminCode())
        ));
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("로그인")
    public TokenResponse signIn(@RequestBody @Valid SignInRequest req) {
        return new TokenResponse(authService.signIn(
                new SignInDto(
                        req.getAccountId(),
                        req.getPassword()
                )
        ));
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("AccessToken 갱신")
    public RefreshResponse refresh(@RequestBody @Valid RefreshRequest req) {
        return new RefreshResponse(
                authService.refresh(req.getRefreshToken())
        );
    }

    @GetMapping("/id-duplicate")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Id 중복확인")
    public boolean checkIdDuplicate(@RequestParam String id) {
        return authService.isIdDuplicate(id);
    }

    @GetMapping("/nickname-duplicate")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("닉네임 중복확인")
    public boolean checkNickNameDuplicate(@RequestParam String nickName) {
        return authService.isNickNameDuplicate(nickName);
    }
}
