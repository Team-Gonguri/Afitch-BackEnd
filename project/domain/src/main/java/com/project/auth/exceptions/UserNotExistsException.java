package com.project.auth.exceptions;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException() {
        super("존재하지 않는 사용자 입니다.");
    }
}
