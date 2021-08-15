package com.project.diet.exceptions;

public class FoodNotExistException extends RuntimeException {
    public FoodNotExistException() {
        super("존재하지 않는 음식정보입니다.");
    }
}
