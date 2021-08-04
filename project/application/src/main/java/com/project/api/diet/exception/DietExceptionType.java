package com.project.api.diet.exception;

import lombok.Getter;

@Getter
public enum DietExceptionType {

    NOT_YOUR_CONTENTS("DIET-001", "식사 열람 권한이 없습니다.");


    private final String code;
    private final String message;

    DietExceptionType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
