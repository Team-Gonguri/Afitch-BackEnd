package com.project.auth.exceptions;

public class NotPositiveNumberException extends RuntimeException {

    public NotPositiveNumberException() {
        super("몸무게와 키는 양수값이어야합니다.");
    }
}
