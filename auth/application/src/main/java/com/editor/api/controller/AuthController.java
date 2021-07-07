package com.editor.api.controller;

import com.editor.model.dto.SignInDto;
import com.editor.model.dto.SignUpDto;
import com.editor.api.request.RefreshRequest;
import com.editor.api.request.SignInRequest;
import com.editor.api.request.SignUpRequest;
import com.editor.api.response.RefreshResponse;
import com.editor.api.response.SignInResponse;
import com.editor.service.AccountService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;

    @PostMapping("/sign-up")
    @ApiOperation("회원가입")
    public SignInResponse signUp(@RequestBody @Valid SignUpRequest req) {
        return new SignInResponse(accountService.signUp(
                new SignUpDto(req.getAccountId(),
                        req.getPassword(),
                        req.getEmail())
        ));
    }

    @PostMapping("/sign-in")
    @ApiOperation("로그인")
    public SignInResponse signIn(@RequestBody @Valid SignInRequest req) {
        return new SignInResponse(accountService.signIn(
                new SignInDto(
                        req.getAccountId(),
                        req.getPassword()
                )
        ));
    }

    @PostMapping("/refresh")
    @ApiOperation("AccessToken 갱신")
    public RefreshResponse refresh(@RequestBody @Valid RefreshRequest req) {
        return new RefreshResponse(
                accountService.refresh(req.getRefreshToken())
        );
    }

    @GetMapping("/id-duplicate")
    @ApiOperation("Id 중복확인")
    public boolean checkIdDuplicate(@RequestParam String id) {
        return accountService.isIdDuplicate(id);
    }
}
