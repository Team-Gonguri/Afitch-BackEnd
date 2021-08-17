package com.project.diet;

import com.project.auth.model.repository.UserRepository;
import com.project.common.TestBase;
import com.project.common.factory.DietFactory;
import com.project.diet.model.repository.FoodRepository;
import com.project.diet.model.repository.FoodWrapperRepository;
import com.project.diet.model.repository.MealRepository;
import com.project.diet.service.DietService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class DietTestBase extends TestBase {

    @Mock
    protected FoodRepository foodRepository;

    @Mock
    protected MealRepository mealRepository;

    @Mock
    protected FoodWrapperRepository foodWrapperRepository;

    @Mock
    protected UserRepository userRepository;

    @InjectMocks
    protected DietService dietService;

    protected DietFactory dietFactory = new DietFactory();
}
