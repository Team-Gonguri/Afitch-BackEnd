package com.project.diet.test;

import com.project.utils.DateUtils;
import com.project.auth.exceptions.UserNotExistsException;
import com.project.diet.DietTestBase;
import com.project.diet.model.dto.SimpleMealDto;
import com.project.diet.model.entity.Meal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class GetDietlTest extends DietTestBase {


    @Test
    @DisplayName("식단 가져오기 성공")
    public void getMealSuccess() {
        List<Meal> meals = dietFactory.getMealList(userFactory.sampleUser);
        Mockito.doReturn(userFactory.sampleOptionalUser)
                .when(userRepository).findById(Mockito.anyLong());

        Mockito.doReturn(meals)
                .when(mealRepository).findByUserAndCreatedAtOrderByTypeAsc(Mockito.any(), Mockito.any());

        List<SimpleMealDto> simpleMealDto = Assertions.assertDoesNotThrow(() -> dietService.getDiet(1L, DateUtils.parseDateToString(new Date())));
        Assertions.assertEquals(4, simpleMealDto.size());
        Assertions.assertAll(
                () -> {
                    for (int i = 0; i < meals.size(); i++)
                        Assertions.assertEquals(meals.get(i).getType(), simpleMealDto.get(i).getType());
                }
        );
    }

    @Test
    @DisplayName("식단 가져오기 실패(존재 하지 않는 사용자)")
    public void getMealFailedBecauseUserNotExist() {
        Mockito.doReturn(Optional.empty())
                .when(userRepository).findById(Mockito.anyLong());

        Assertions.assertThrows(UserNotExistsException.class, () -> dietService.getDiet(1L, DateUtils.parseDateToString(new Date())));
    }
}
