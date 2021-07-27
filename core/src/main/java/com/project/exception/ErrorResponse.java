package com.project.exception;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorResponse {
    private String message;
    private String errorCode;
    private Date timeStamp;


    public ErrorResponse(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
        timeStamp = new Date();
    }
}
