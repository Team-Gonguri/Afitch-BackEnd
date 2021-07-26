package com.project.security.exceptions;

import lombok.Getter;

@Getter
public enum SecurityExceptionType {

    NOT_BEARER_FORMAT("AUTH-001", "Bearer 토큰이 아닙니다"),
    MALFORMED_TOKEN("AUTH-002", "손상된 토큰입니다"),
    EXPIRED_TOKEN("AUTH-003", "만료된 토큰입니다."),
    INVALID_TOKEN("AUTH-004", "유효하지 않은 토큰입니다."),
    SIGNATURE_EXCEPTION("AUTH-005", "시그니쳐 검증에 실패하였습니다.");


    private final String code;
    private final String message;

    SecurityExceptionType(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

}
