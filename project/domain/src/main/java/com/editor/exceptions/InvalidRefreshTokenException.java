package com.editor.exceptions;

import lombok.Getter;

@Getter
public class InvalidRefreshTokenException extends RuntimeException {
    public InvalidRefreshTokenException() {
        super("유효하지않은 Refresh Token 입니다.");
    }
}
