package com.project.diet.service;

import com.project.common.dto.PageDto;
import com.project.common.utils.PageUtils;
import com.project.diet.model.dto.FoodDto;
import com.project.diet.model.entity.Food;
import com.project.diet.model.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public PageDto findFoods(String keyword, int page) {
        Page<Food> food = foodRepository.findFoodByNameContaining(keyword, PageUtils.normalPaging(page, Sort.Direction.DESC));
        return new PageDto(food.getContent().stream().map(FoodDto::new).collect(Collectors.toList()), food.getNumber(),food.getTotalPages());
    }

}
