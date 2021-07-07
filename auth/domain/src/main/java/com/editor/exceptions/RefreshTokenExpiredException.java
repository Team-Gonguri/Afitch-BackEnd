package com.editor.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class RefreshTokenExpiredException extends RuntimeException {
    public RefreshTokenExpiredException() {
        super("만료된 Refresh Token 입니다.");
    }
}
