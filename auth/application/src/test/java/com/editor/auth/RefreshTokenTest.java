package com.editor.auth;


import com.editor.common.AuthTestBase;
import com.editor.security.enums.TokenType;
import com.editor.security.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class RefreshTokenTest extends AuthTestBase {


    protected String validAccessToken = null;
    protected String inValidAccessToken = null;
    protected String validRefreshToken = null;
    protected String inValidRefreshToken = null;
    protected String expiredAccessToken = null;
    protected String expiredRefreshToken = null;

    @BeforeEach
    public void setUp() {
        validAccessToken = jwtProvider.getToken(1L, UserRole.ROLE_USER, TokenType.ACCESS);
        validRefreshToken = jwtProvider.getToken(1L, UserRole.ROLE_SERVER, TokenType.REFRESH);

        expiredAccessToken = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6IlJPTEVfVVNFUiIsImlhdCI6MTYyNTcyNDk2OSwiZXhwIjoxNjI1NzI0OTcwfQ.Fv-fptrtlYmVPl7bm6m-3w1MW8cT84d9RrS_UcPM2aA";
        expiredRefreshToken = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6IlJPTEVfVVNFUiIsImlhdCI6MTYyNTcyNTEwNCwiZXhwIjoxNjI1NzI1MTA2fQ.IXoYXZlFK6SvxfqeVBUQfCu3IJdafAbKBi9_7RpsH94";
        inValidAccessToken = "InvalidAccess";
        inValidRefreshToken = "InvalidRefresh";
    }

    @Test
    @DisplayName("토큰 갱신 성공")
    public void tokenRefreshSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/refresh")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRefreshToken)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("accessToken").isString());
    }

    @Test
    @DisplayName("토큰 갱신 실패(유효하지 않은 토큰)")
    public void tokenRefreshFailedBecauseOfInvalidToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/refresh")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inValidRefreshToken)))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value("AUTH-007"));
    }

    @Test
    @DisplayName("토큰 갱신 실패(만료된 토큰)")
    public void tokenRefreshFailedBecauseOfExpiredToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/refresh")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expiredRefreshToken)))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value("AUTH-007"));
    }

}
