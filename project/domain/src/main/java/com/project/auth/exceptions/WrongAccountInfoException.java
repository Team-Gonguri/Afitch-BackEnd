package com.project.auth.exceptions;

public class WrongAccountInfoException extends RuntimeException {

    public WrongAccountInfoException() {
        super("가입하지 않은 아이디거나, 잘못된 비밀번호 입니다.");
    }
}
