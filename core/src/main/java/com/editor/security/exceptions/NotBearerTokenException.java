package com.editor.security.exceptions;

public class NotBearerTokenException extends RuntimeException {
    public NotBearerTokenException() {
        super("Bearer Token 형식이 아닙니다.");
    }
}
