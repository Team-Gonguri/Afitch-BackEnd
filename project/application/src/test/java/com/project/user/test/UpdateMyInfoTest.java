package com.project.user.test;

import com.project.api.auth.request.UpdateUserInfoRequest;
import com.project.user.UserTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class UpdateMyInfoTest extends UserTestBase {
    @Test
    @DisplayName("내 정보 수정하기 성공")
    public void updateMyInfoSuccess() throws Exception {
        UpdateUserInfoRequest req = new UpdateUserInfoRequest("꼬마거인", 180.0, 70);

        mockMvc.perform(MockMvcRequestBuilders.put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                .header("Authorization", "Bearer " + validAccessToken1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("nickName").value(req.getNickName()))
                .andExpect(MockMvcResultMatchers.jsonPath("weight").value(req.getWeight()))
                .andExpect(MockMvcResultMatchers.jsonPath("height").value(req.getHeight()));
    }

    @Test
    @DisplayName("내 정보 수정하기 실패(키나 몸무게의 값이 양수가아님)")
    public void updateMyInfoFailedBecauseParametersAreNotPositive() throws Exception {
        UpdateUserInfoRequest req = new UpdateUserInfoRequest("꼬마거인", 0, -20);
        mockMvc.perform(MockMvcRequestBuilders.put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                .header("Authorization", "Bearer " + validAccessToken1))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value("USER-002"));
    }

    @Test
    @DisplayName("내 정보 수정하기 실패(존재하지 않는 사용자)")
    public void updateMyInfoFailedBecauseUserNotExists() throws Exception {
        UpdateUserInfoRequest req = new UpdateUserInfoRequest("꼬마거인", 180.0, 70);
        mockMvc.perform(MockMvcRequestBuilders.put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                .header("Authorization", "Bearer " + notExistAccessToken))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value("USER-001"));
    }

}
