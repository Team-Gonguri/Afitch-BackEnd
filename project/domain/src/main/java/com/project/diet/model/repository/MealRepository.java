package com.project.diet.model.repository;

import com.project.diet.model.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal,Long> {
}
