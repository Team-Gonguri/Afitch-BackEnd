package com.project.api.auth.exception;

import com.project.exception.ErrorResponse;
import com.project.auth.exceptions.InvalidRefreshTokenException;
import com.project.auth.exceptions.WrongAccountInfoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(WrongAccountInfoException.class)
    public ErrorResponse handleWrongAccountInfoException(Exception e) {
        return new ErrorResponse(AuthExceptionType.WRONG_INFO.getMessage(), AuthExceptionType.WRONG_INFO.getCode());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ErrorResponse handlerRefreshTokenExpiredException(Exception e) {
        return new ErrorResponse(AuthExceptionType.INVALID_REFRESH.getMessage(), AuthExceptionType.INVALID_REFRESH.getCode());
    }
}
