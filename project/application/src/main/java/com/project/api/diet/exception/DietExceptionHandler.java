package com.project.api.diet.exception;

import com.project.exception.ErrorResponse;
import com.project.exception.NotYourContentsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DietExceptionHandler {

    @ExceptionHandler(NotYourContentsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse notYourContentsExceptionHandler(Exception e) {
        return new ErrorResponse(DietExceptionType.NOT_YOUR_CONTENTS.getMessage(), DietExceptionType.NOT_YOUR_CONTENTS.getCode());
    }
}
