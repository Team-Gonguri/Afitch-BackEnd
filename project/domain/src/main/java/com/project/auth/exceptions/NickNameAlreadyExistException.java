package com.project.auth.exceptions;

public class NickNameAlreadyExistException extends RuntimeException {
    public NickNameAlreadyExistException() {
        super("이미 존재하는 닉네임 입니다.");
    }
}

