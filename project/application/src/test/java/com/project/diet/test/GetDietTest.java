package com.project.diet.test;

import com.project.utils.DateUtils;
import com.project.diet.DietTestBase;
import com.project.diet.model.entity.enums.MealType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

public class GetDietTest extends DietTestBase {

    @Test
    @DisplayName("특정 일자 식단 가져오기 성공")
    public void getDietSuccess() throws Exception {
        createMeal(MealType.BREAKFAST);
        createMeal(MealType.LUNCH);
        createMeal(MealType.DINNER);
        createMeal(MealType.SNACK);

        mockMvc.perform(MockMvcRequestBuilders.get("/diets/diet")
                .header("Authorization", "Bearer " + validAccessToken1)
                .param("date", DateUtils.parseDateToString(new Date())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("diet").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("ingredients").isNotEmpty());
    }

    @Test
    @DisplayName("특정 날짜 식단 가져오기 실패(날짜 형식 오류)")
    public void getDietFailedBecauseDateFormatIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/diets/diet")
                .header("Authorization", "Bearer " + validAccessToken1)
                .param("date", "invalidParse"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value("SYSTEM-001"));
    }

    @Test
    @DisplayName("특정 날짜 식단 가져오기 실패(존재 하지 않는 사용자)")
    public void getDietFailedBecauseUserNotExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/diets/diet")
                .header("Authorization", "Bearer " + notExistAccessToken)
                .param("date", DateUtils.parseDateToString(new Date())))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value("USER-001"));
    }
}
