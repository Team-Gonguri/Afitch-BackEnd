package com.project.exception;

public class NotYourContentsException extends RuntimeException {
    public NotYourContentsException() {
        super("접근 권한이 없습니다.");
    }
}

