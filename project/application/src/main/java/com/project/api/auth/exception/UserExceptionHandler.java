package com.project.api.auth.exception;

import com.project.auth.exceptions.NotPositiveNumberException;
import com.project.auth.exceptions.UserNotExistsException;
import com.project.auth.exceptions.WrongAccountInfoException;
import com.project.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserNotExistsException.class)
    public ErrorResponse userNotExistsExceptionHandler(Exception e) {
        return new ErrorResponse(UserExceptionType.USER_NOT_EXIST.getMessage(), UserExceptionType.USER_NOT_EXIST.getCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotPositiveNumberException.class)
    public ErrorResponse notPositiveNumberExceptionHandler(Exception e) {
        return new ErrorResponse(UserExceptionType.NOT_POSITIVE_NUMBER.getMessage(), UserExceptionType.NOT_POSITIVE_NUMBER.getCode());
    }
}
