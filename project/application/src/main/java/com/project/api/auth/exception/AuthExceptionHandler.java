package com.project.api.auth.exception;

import com.project.auth.exceptions.*;
import com.project.exception.ErrorResponse;
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

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AccountAlreadyExistException.class)
    public ErrorResponse handleAccountAlreadyExistException(Exception e) {
        return new ErrorResponse(AuthExceptionType.EXIST_ACCOUNT.getMessage(), AuthExceptionType.EXIST_ACCOUNT.getCode());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(NickNameAlreadyExistException.class)
    public ErrorResponse handleNicknameAlreadyExistException(Exception e) {
        return new ErrorResponse(AuthExceptionType.EXIST_NICKNAME.getMessage(), AuthExceptionType.EXIST_NICKNAME.getCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidAdminCodeException.class)
    public ErrorResponse handleInvalidAdminCodeException(Exception e) {
        return new ErrorResponse(AuthExceptionType.INVALID_ADMIN_CODE.getMessage(), AuthExceptionType.INVALID_ADMIN_CODE.getCode());
    }
}
