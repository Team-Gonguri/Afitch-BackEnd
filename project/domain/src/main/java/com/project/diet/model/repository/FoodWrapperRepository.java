package com.project.diet.model.repository;

import com.project.diet.model.entity.FoodWrapper;
import com.project.diet.model.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodWrapperRepository extends JpaRepository<FoodWrapper, Long> {
    List<FoodWrapper> findAllByMeal(Meal meal);

    void deleteAllByMeal(Meal meal);
}
