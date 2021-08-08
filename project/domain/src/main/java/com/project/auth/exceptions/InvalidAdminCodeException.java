package com.project.auth.exceptions;

public class InvalidAdminCodeException extends RuntimeException {
    public InvalidAdminCodeException() {
        super("관리자 코드가 올바르지 않습니다.");
    }
}
