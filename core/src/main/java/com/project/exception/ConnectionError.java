package com.project.exception;

public class ConnectionError extends RuntimeException {
    public ConnectionError(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
    }
}
