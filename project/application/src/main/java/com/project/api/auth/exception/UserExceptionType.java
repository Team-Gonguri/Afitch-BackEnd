package com.project.api.auth.exception;

import lombok.Getter;

@Getter
public enum UserExceptionType {

    USER_NOT_EXIST("USER-001", "존재하지 않는 사용자입니다."),
    NOT_POSITIVE_NUMBER("USER-002", "몸무게나 키의 값은 양수여야만 합니다.");

    private final String code;
    private final String message;

    UserExceptionType(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
