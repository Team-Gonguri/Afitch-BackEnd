package com.project.exception;

import lombok.Getter;

@Getter
public enum GlobalExceptionType {

    INVALID_ARGUMENT("SYSTEM-001", "잘못된 요청 파라미터 입니다."),
    UNEXPECTED_SERVER_ERROR("SYSTEM-002", "예기치못한 서버 오류가 발생하였습니다.");


    private final String code;
    private final String message;

    GlobalExceptionType(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

}
