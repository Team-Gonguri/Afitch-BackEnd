package com.project.diet.service;

import com.project.diet.model.repository.FoodRepository;
import com.project.diet.model.repository.FoodWrapperRepository;
import com.project.diet.model.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DietService {
    private final FoodRepository foodRepository;
    private final FoodWrapperRepository foodWrapperRepository;
    private final MealRepository mealRepository;
}
