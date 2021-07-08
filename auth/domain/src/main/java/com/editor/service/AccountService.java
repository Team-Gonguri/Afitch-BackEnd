package com.editor.service;

import com.editor.exceptions.RefreshTokenExpiredException;
import com.editor.exceptions.WrongAccountInfoException;
import com.editor.model.dto.SignInDto;
import com.editor.model.dto.SignUpDto;
import com.editor.model.dto.TokenDto;
import com.editor.model.entity.Account;
import com.editor.model.repository.AccountRepository;
import com.editor.security.JwtProvider;
import com.editor.security.enums.TokenType;
import com.editor.security.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public TokenDto signIn(SignInDto signInDto) {
        Account account = accountRepository.findByAccountId(signInDto.getAccountId())
                .orElseThrow(WrongAccountInfoException::new);

        if (passwordEncoder.matches(signInDto.getPassword(), account.getPassword()))
            return new TokenDto(
                    jwtProvider.getToken(account.getId(), account.getUserRole(), TokenType.ACCESS),
                    jwtProvider.getToken(account.getId(), account.getUserRole(), TokenType.REFRESH)
            );
        else throw new WrongAccountInfoException();
    }

    /**
     * true면 회원가입 가능, false면 중복임으로 불가
     */
    public boolean isIdDuplicate(String accountId) {
        return !accountRepository.existsByAccountId(accountId);
    }

    public TokenDto signUp(SignUpDto signUpDto) {
        Account account =
                accountRepository.save(
                        new Account(
                                null,
                                signUpDto.getAccountId(),
                                passwordEncoder.encode(signUpDto.getPassword()),
                                signUpDto.getEmail(),
                                UserRole.ROLE_USER
                        ));
        return new TokenDto(
                jwtProvider.getToken(account.getId(), account.getUserRole(), TokenType.ACCESS),
                jwtProvider.getToken(account.getId(), account.getUserRole(), TokenType.REFRESH)
        );
    }

    public String refresh(String refreshToken) {
        try {
            if (jwtProvider.validateToken(refreshToken)) {
                return jwtProvider.getToken(jwtProvider.getUserIdFromToken(refreshToken),
                        jwtProvider.getRoleFromToken(refreshToken),
                        TokenType.ACCESS);
            } else
                throw new RefreshTokenExpiredException();
        } catch (Exception e) {
            throw new RefreshTokenExpiredException();
        }
    }
}