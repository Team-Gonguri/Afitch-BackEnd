package com.editor.auth;

import com.editor.api.request.SignInRequest;
import com.editor.api.request.SignUpRequest;
import com.editor.common.AuthTestBase;
import com.editor.model.entity.Account;
import com.editor.security.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class SignInTest extends AuthTestBase {

    SignInRequest validSignInRequest = new SignInRequest("ktj7916", "1q2w3e4r!!");
    SignInRequest inValidSignInRequest = new SignInRequest("ktj7916", "1q2w3e4r");
    SignInRequest emptyRequest = new SignInRequest("ktj7916", "");

    @BeforeEach
    public void setUp() {
        SignUpRequest signUpRequest = new SignUpRequest("ktj7916", "1q2w3e4r!!", "ktj7916@naver.com");
        accountRepository.save(
                new Account(
                        null,
                        signUpRequest.getAccountId(),
                        passwordEncoder.encode(signUpRequest.getPassword()),
                        signUpRequest.getEmail(),
                        UserRole.ROLE_USER));
    }


    @Test
    @DisplayName("로그인 성공")
    public void signInSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validSignInRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("accessToken").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("refreshToken").isString());
    }

    @Test
    @DisplayName("로그인 실패(알맞지 않은 정보)")
    public void signInFailedBecauseOfInvalidInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inValidSignInRequest)))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value("AUTH-006"));
    }

    @Test
    @DisplayName("로그인 실패(누락된 정보)")
    public void signInFailedBecauseOfEmptyValue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emptyRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value("SYSTEM-001"));
    }
}
