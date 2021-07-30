package com.project.diet.service;

import com.project.common.dto.PageDto;
import com.project.common.utils.PageUtils;
import com.project.diet.model.entity.Food;
import com.project.diet.model.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {


    private final FoodRepository foodRepository;

    @Cacheable(cacheNames = "food_categories")
    @Transactional(readOnly = true)
    public List<String> getFoodCategories() {
        return foodRepository.findAllDistinctFoodCategories();
    }

    @Transactional(readOnly = true)
    public PageDto<Food> findFoods(String keyword, int page) {
        return new PageDto(foodRepository.findFoodByNameContaining(keyword, PageUtils.of(page)));
    }

}
