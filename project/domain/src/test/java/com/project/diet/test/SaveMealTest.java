package com.project.diet.test;

import com.project.diet.DietTestBase;
import com.project.diet.model.dto.FoodDto;
import com.project.diet.model.dto.FoodWrapperDto;
import com.project.diet.model.entity.Meal;
import com.project.diet.model.entity.enums.MealType;
import com.project.utils.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SaveMealTest extends DietTestBase {

    @Test
    @DisplayName("식사 저장 성공")
    public void saveMealSuccess() {
        FoodWrapperDto foodWrapperDto = new FoodWrapperDto(2, new FoodDto(dietFactory.getFood()));
        Meal meal = dietFactory.getMeal(userFactory.sampleUser, MealType.BREAKFAST);

        Mockito.doReturn(userFactory.sampleOptionalUser)
                .when(userRepository).findById(Mockito.anyLong());

        Mockito.doReturn(Optional.of(dietFactory.getFood()))
                .when(foodRepository).findById(Mockito.anyLong());

        Mockito.doReturn(meal)
                .when(mealRepository).findByUserAndTypeAndCreatedAt(Mockito.any(), Mockito.any(), Mockito.any());

        Mockito.doReturn(List.of())
                .when(foodWrapperRepository).saveAll(Mockito.anyCollection());

        Assertions.assertDoesNotThrow(() -> dietService.saveMeal(1L, List.of(foodWrapperDto), MealType.BREAKFAST, DateUtils.parseDateToSimpleString(new Date())));
    }
}
