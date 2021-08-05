package com.project.diet.test;

import com.project.utils.DateUtils;
import com.project.api.diet.request.MealSaveRequest;
import com.project.diet.DietTestBase;
import com.project.diet.model.entity.enums.MealType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.List;

public class SaveMealTest extends DietTestBase {

    @Test
    @DisplayName("특정 날짜 식단 저장하기 성공")
    public void getDietSuccess() throws Exception {
        MealSaveRequest req = new MealSaveRequest(MealType.SNACK, getFoodWrapperDtoList(), DateUtils.parseDateToString(new Date()));

        mockMvc.perform(MockMvcRequestBuilders.post("/diets/meal")
                .header("Authorization", "Bearer " + validAccessToken1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("foods[0].food.id").value(req.getFoods().get(0).getFood().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("createdAt").value(req.getDate()))
                .andExpect(MockMvcResultMatchers.jsonPath("type").value(req.getType().toString()));
    }

    @Test
    @DisplayName("특정 날짜 식단 저장하기 실패(날짜 형식 오류)")
    public void saveMealFailedBecauseDateFormatIsInvalid() throws Exception {
        MealSaveRequest req = new MealSaveRequest(MealType.SNACK, getFoodWrapperDtoList(), "InvalidDateFormat");
        mockMvc.perform(MockMvcRequestBuilders.post("/diets/meal")
                .header("Authorization", "Bearer " + validAccessToken1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value("SYSTEM-001"));
    }

    @Test
    @DisplayName("특정날짜 식단 저장하기 실패(음식 부분이 비어있을 경우)")
    public void saveMealFailedBecauseFoodIsEmpty() throws Exception {
        MealSaveRequest req = new MealSaveRequest(MealType.SNACK, List.of(), "InvalidDateFormat");
        mockMvc.perform(MockMvcRequestBuilders.post("/diets/meal")
                .header("Authorization", "Bearer " + validAccessToken1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value("SYSTEM-001"));
    }

    @Test
    @DisplayName("특정 날짜 식단 저장하기 실패(존재하지 않는 사용자)")
    public void saveMealFailedBecauseUserNotExists() throws Exception {
        MealSaveRequest req = new MealSaveRequest(MealType.SNACK, getFoodWrapperDtoList(), DateUtils.parseDateToString(new Date()));
        mockMvc.perform(MockMvcRequestBuilders.post("/diets/meal")
                .header("Authorization", "Bearer " + notExistAccessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value("USER-001"));
    }


}
