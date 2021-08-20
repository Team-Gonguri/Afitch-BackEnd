package com.project.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.ParseException;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, ParseException.class})
    public ErrorResponse handleMethodArgumentNotValidException(Exception e) {
        return new ErrorResponse(GlobalExceptionType.INVALID_ARGUMENT.getMessage(), GlobalExceptionType.INVALID_ARGUMENT.getCode());
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(UnExpectedError.class)
    public ErrorResponse handleConnectionError(Exception e) {
        return new ErrorResponse(GlobalExceptionType.UNEXPECTED_SERVER_ERROR.getMessage(), GlobalExceptionType.UNEXPECTED_SERVER_ERROR.getCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFileTypeException.class)
    public ErrorResponse handleInvalidFileTypeException() {
        return new ErrorResponse(GlobalExceptionType.INVALID_FILE_TYPE.getMessage(), GlobalExceptionType.INVALID_FILE_TYPE.getCode());
    }
}
