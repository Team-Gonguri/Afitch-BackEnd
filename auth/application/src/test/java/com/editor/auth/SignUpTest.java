package com.editor.auth;

import com.editor.api.request.SignUpRequest;
import com.editor.common.AuthTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class SignUpTest extends AuthTestBase {

    @Test
    @DisplayName("회원 가입성공")
    public void signUpSuccess() throws Exception {
        SignUpRequest signUpRequest = new SignUpRequest("ktj7916", "1q2w3e4r!!", "ktj7916@naver.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("accessToken").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("refreshToken").isString());
    }

    @Test
    @DisplayName("회원가입 실패(누락된 정보)")
    public void signUpFailedBecauseOfEmptyValue() throws Exception {
        SignUpRequest signUpRequest = new SignUpRequest("ktj7916", "1q2w3e4r!!", "ktj7916");
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value("SYSTEM-001"));

    }

    @Test
    @DisplayName("회원가입 실패(이메일 형식)")
    public void signUpFailedBecauseOfInvalidEmailFormat() throws Exception {
        SignUpRequest signUpRequest = new SignUpRequest("ktj7916", "", "ktj7916@naver.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value("SYSTEM-001"));
    }
}
