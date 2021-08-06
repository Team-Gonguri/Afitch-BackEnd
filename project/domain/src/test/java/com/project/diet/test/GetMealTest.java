package com.project.diet.test;

import com.project.utils.DateUtils;
import com.project.auth.exceptions.UserNotExistsException;
import com.project.auth.model.entity.User;
import com.project.diet.DietTestBase;
import com.project.diet.model.dto.MealDto;
import com.project.diet.model.entity.Meal;
import com.project.diet.model.entity.enums.MealType;
import com.project.exception.NotYourContentsException;
import com.project.security.enums.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class GetMealTest extends DietTestBase {

    @Test
    @DisplayName("식사 가져오기 성공")
    public void getMealSuccess() {
        Meal meal = dietFactory.getMeal(userFactory.sampleUser, MealType.BREAKFAST);

        Mockito.doReturn(
                Optional.of(meal))
                .when(mealRepository).findById(Mockito.anyLong());

        Mockito.doReturn(
                userFactory.sampleOptionalUser
        ).when(userRepository).findById(Mockito.anyLong());

        MealDto mealDto = Assertions.assertDoesNotThrow(() -> dietService.getMeal(1L, 1L));
        Assertions.assertEquals(meal.getType(), mealDto.getType());
        Assertions.assertEquals(DateUtils.parseDateToSimpleString(meal.getCreatedAt()), mealDto.getCreatedAt());
    }

    @Test
    @DisplayName("식사 가져오기 실패(존재 하지 않는 사용자)")
    public void getMealFailedBecauseUserNotExist() {
        Mockito.doReturn(Optional.empty())
                .when(userRepository).findById(Mockito.anyLong());

        Assertions.assertThrows(UserNotExistsException.class, () -> dietService.getMeal(1L, 1L));
    }

    @Test
    @DisplayName("식사 가져오기 실패 (내 식사정보가 아님)")
    public void getMealFailedBecauseNotMyContent() {
        Meal meal = dietFactory.getMeal(userFactory.sampleUser, MealType.BREAKFAST);
        Mockito.doReturn(
                Optional.of(meal))
                .when(mealRepository).findById(Mockito.anyLong());

        Mockito.doReturn(
                Optional.of(
                        new User(
                                "testUser",
                                "testPassword",
                                "testNickName",
                                UserRole.ROLE_ADMIN
                        )
                )
        ).when(userRepository).findById(Mockito.anyLong());

        Assertions.assertThrows(NotYourContentsException.class, () -> dietService.getMeal(1L, 1L));
    }
}
