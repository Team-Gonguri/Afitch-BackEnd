package com.project.diet.exceptions;

public class MealNotExistsException extends RuntimeException {
    public MealNotExistsException() {
        super("존재하지 않는 식사정보 입니다.");
    }
}
