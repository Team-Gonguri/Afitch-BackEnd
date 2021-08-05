package com.project.exception;

public class UnExpectedError extends RuntimeException {

    public UnExpectedError(String msg) {
        super(msg);
    }

    public UnExpectedError(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
    }
}
