package com.project.diet.service;

import com.project.diet.model.repository.FoodRepository;
import com.project.diet.model.repository.FoodWrapperRepository;
import com.project.diet.model.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DietService {
    private final FoodRepository foodRepository;
    private final FoodWrapperRepository foodWrapperRepository;
    private final MealRepository mealRepository;


    @Cacheable(cacheNames = "food_categories")
    @Transactional(readOnly = true)
    public List<String> getFoodCategories() {
        return foodRepository.findAllDistinctFoodCategories();
    }
}
