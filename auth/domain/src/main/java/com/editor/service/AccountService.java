package com.editor.service;

import com.editor.model.dto.SignInDto;
import com.editor.model.dto.SignUpDto;
import com.editor.model.dto.TokenDto;
import com.editor.model.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public TokenDto signIn(SignInDto signInDto) {
        return null;
    }

    public TokenDto signUp(SignUpDto signUpDto) {
        return null;
    }

    public String refresh(String refreshToken) {
        return null;
    }
}
