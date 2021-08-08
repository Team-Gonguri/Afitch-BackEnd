package com.project.api.auth.exception;

import lombok.Getter;

@Getter
public enum AuthExceptionType {

    WRONG_INFO("AUTH-006", "가입하지 않은 아이디거나, 잘못된 비밀번호 입니다."),
    INVALID_REFRESH("AUTH-007", "Refresh Token이 유효하지 않습니다."),
    EXIST_ACCOUNT("AUTH-008", "이미 존재하는 ID 입니다"),
    EXIST_NICKNAME("AUTH-009", "이미 존재하는 닉네임 입니다.");

    private final String code;
    private final String message;

    AuthExceptionType(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

}
