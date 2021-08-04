package com.project.auth.test;


import com.project.auth.AuthTestBase;
import com.project.common.CommonTestBase;
import com.project.security.enums.TokenType;
import com.project.security.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class RefreshTokenTest extends AuthTestBase {

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
