package com.project.diet.model.repository;

import com.project.diet.model.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query("SELECT DISTINCT p.foodCategory from Food p")
    List<String> findAllDistinctFoodCategories();

    Page<Food> findFoodByNameContaining(String keyword, Pageable pageable);
}
