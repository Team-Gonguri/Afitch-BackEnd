package com.project.auth.service;

import com.project.auth.exceptions.*;
import com.project.auth.model.dto.SignInDto;
import com.project.auth.model.dto.SignUpDto;
import com.project.auth.model.dto.TokenDto;
import com.project.auth.model.entity.User;
import com.project.auth.model.repository.UserRepository;
import com.project.security.JwtProvider;
import com.project.security.enums.TokenType;
import com.project.security.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Value("${application.admin.code}")
    private String adminCode;

    public TokenDto signIn(SignInDto signInDto) {
        User user = userRepository.findByAccountId(signInDto.getAccountId())
                .orElseThrow(WrongAccountInfoException::new);

        if (passwordEncoder.matches(signInDto.getPassword(), user.getPassword()))
            return new TokenDto(
                    jwtProvider.getToken(user.getId(), user.getUserRole(), TokenType.ACCESS),
                    jwtProvider.getToken(user.getId(), user.getUserRole(), TokenType.REFRESH)
            );
        else throw new WrongAccountInfoException();
    }

    /**
     * true면 회원가입 가능, false면 중복임으로 불가
     */
    public boolean isIdDuplicate(String accountId) {
        return !userRepository.existsByAccountId(accountId);
    }

    public boolean isNickNameDuplicate(String nickName) {
        return !userRepository.existsByNickName(nickName);
    }

    public TokenDto signUp(SignUpDto signUpDto) {
        if (userRepository.existsByAccountId(signUpDto.getAccountId()))
            throw new AccountAlreadyExistException();
        if (userRepository.existsByNickName(signUpDto.getNickName()))
            throw new NickNameAlreadyExistException();

        User user;
        if (signUpDto.getRole().equals(UserRole.ROLE_USER)) {
            user = userRepository.save(
                    new User(
                            signUpDto.getAccountId(),
                            passwordEncoder.encode(signUpDto.getPassword()),
                            signUpDto.getNickName(),
                            UserRole.ROLE_USER
                    ));
        } else {
            if (!signUpDto.getAdminCode().equals(adminCode))
                throw new InvalidAdminCodeException();
            user = userRepository.save(
                    new User(
                            signUpDto.getAccountId(),
                            passwordEncoder.encode(signUpDto.getPassword()),
                            signUpDto.getNickName(),
                            UserRole.ROLE_ADMIN
                    ));
        }
        return new TokenDto(
                jwtProvider.getToken(user.getId(), user.getUserRole(), TokenType.ACCESS),
                jwtProvider.getToken(user.getId(), user.getUserRole(), TokenType.REFRESH)
        );
    }

    public String refresh(String refreshToken) {
        try {
            if (jwtProvider.validateToken(refreshToken)) {
                return jwtProvider.getToken(jwtProvider.getUserIdFromToken(refreshToken),
                        jwtProvider.getRoleFromToken(refreshToken),
                        TokenType.ACCESS);
            } else
                throw new InvalidRefreshTokenException();
        } catch (Exception e) {
            throw new InvalidRefreshTokenException();
        }
    }
}