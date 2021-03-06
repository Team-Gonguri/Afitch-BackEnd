package com.project.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.auth.model.repository.UserRepository;
import com.project.auth.service.AuthService;
import com.project.security.JwtProvider;
import com.project.security.JwtTokenDto;
import com.project.security.enums.TokenType;
import com.project.security.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Rollback
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class CommonTestBase {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected JwtProvider jwtProvider;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected AuthService authService;

    protected JwtTokenDto validAccessToken1 = null;
    protected JwtTokenDto validAccessToken2 = null;
    protected JwtTokenDto notExistAccessToken = null;
    protected JwtTokenDto inValidAccessToken = null;
    protected JwtTokenDto validRefreshToken = null;
    protected JwtTokenDto inValidRefreshToken = null;
    protected JwtTokenDto expiredAccessToken = null;
    protected JwtTokenDto expiredRefreshToken = null;

    @Order(2)
    @BeforeEach
    public void createToken() {
        validAccessToken1 = jwtProvider.getToken(9999L, UserRole.ROLE_USER, TokenType.ACCESS);
        validAccessToken2 = jwtProvider.getToken(9998L, UserRole.ROLE_USER, TokenType.ACCESS);
        validRefreshToken = jwtProvider.getToken(9999L, UserRole.ROLE_USER, TokenType.REFRESH);
        notExistAccessToken = jwtProvider.getToken(100000L, UserRole.ROLE_USER, TokenType.ACCESS);

        expiredAccessToken = new JwtTokenDto("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6IlJPTEVfVVNFUiIsImlhdCI6MTYyNTcyNDk2OSwiZXhwIjoxNjI1NzI0OTcwfQ.Fv-fptrtlYmVPl7bm6m-3w1MW8cT84d9RrS_UcPM2aA", null);
        expiredRefreshToken = new JwtTokenDto("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6IlJPTEVfVVNFUiIsImlhdCI6MTYyNTcyNTEwNCwiZXhwIjoxNjI1NzI1MTA2fQ.IXoYXZlFK6SvxfqeVBUQfCu3IJdafAbKBi9_7RpsH94", null);
        inValidAccessToken = new JwtTokenDto("InvalidAccess", null);
        inValidRefreshToken = new JwtTokenDto("InvalidRefresh", null);
    }

}
