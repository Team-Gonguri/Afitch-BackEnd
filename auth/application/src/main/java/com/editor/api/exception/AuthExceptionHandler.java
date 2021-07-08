package com.editor.api.exception;

import com.editor.exception.ErrorResponse;
import com.editor.exceptions.RefreshTokenExpiredException;
import com.editor.exceptions.WrongAccountInfoException;
import org.hibernate.NonUniqueResultException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ErrorResponse handlerRefreshTokenExpiredException(Exception e) {
        return new ErrorResponse(AuthExceptionType.INVALID_REFRESH.getMessage(), AuthExceptionType.INVALID_REFRESH.getCode());
    }
}
