package com.project.auth.exceptions;

public class AccountAlreadyExistException extends RuntimeException {
    public AccountAlreadyExistException() {
        super("존재하는 ID 입니다.");
    }
}
