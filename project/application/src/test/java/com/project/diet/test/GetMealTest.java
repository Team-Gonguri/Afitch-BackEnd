package com.project.diet.test;

import com.project.utils.DateUtils;
import com.project.diet.DietTestBase;
import com.project.diet.model.dto.MealDto;
import com.project.diet.model.entity.enums.MealType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

public class GetMealTest extends DietTestBase {

    @Test
    @DisplayName("식사 가져오기 성공")
    public void getMealSuccess() throws Exception {
        MealDto dto = createMeal(MealType.BREAKFAST);

        mockMvc.perform(MockMvcRequestBuilders.get("/diets/meal/" + dto.getId())
                .header("Authorization", "Bearer " + validAccessToken1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(dto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("type").value(dto.getType().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("createdAt").value(DateUtils.parseDateToString(new Date())))
                .andExpect(MockMvcResultMatchers.jsonPath("foods[0].food.id").value(dto.getFoods().get(0).getFood().getId()));

    }

    @Test
    @DisplayName("식사 가져오기 실패(존재하지 않는 사용자)")
    public void getMealFailedBecauseUserNotExists() throws Exception {
        MealDto dto = createMeal(MealType.BREAKFAST);
        mockMvc.perform(MockMvcRequestBuilders.get("/diets/meal/" + dto.getId())
                .header("Authorization", "Bearer " + notExistAccessToken))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value("USER-001"));
    }

    @Test
    @DisplayName("식사 가져오기 실패(나의 컨텐츠가 아님)")
    public void getMealFailedBecauseNotMyContents() throws Exception {
        MealDto dto = createMeal(MealType.BREAKFAST);
        mockMvc.perform(MockMvcRequestBuilders.get("/diets/meal/" + dto.getId())
                .header("Authorization", "Bearer " + validAccessToken2))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("errorCode").value("DIET-001"));
    }
}
