package com.project.user.test;

import com.project.user.UserTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class GetMyInfoTest extends UserTestBase {

    @Test
    @DisplayName("내 정보 가져오기 성공")
    public void getMyInfoSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .header("Authorization", "Bearer " + validAccessToken1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("nickName").value("test1"))
                .andExpect(MockMvcResultMatchers.jsonPath("weight").value(57.0))
                .andExpect(MockMvcResultMatchers.jsonPath("height").value(170.0));
    }

    @Test
    @DisplayName("내 정보 가져오기 실패(존재하지 않는 사용자)")
    public void getMyInfoFailedBecauseUserNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .header("Authorization", "Bearer " + notExistAccessToken))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value("USER-001"));
    }
}
