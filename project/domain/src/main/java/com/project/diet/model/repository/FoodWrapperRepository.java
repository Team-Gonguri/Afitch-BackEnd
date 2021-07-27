package com.project.diet.model.repository;

import com.project.diet.model.entity.FoodWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodWrapperRepository extends JpaRepository<FoodWrapper, Long> {
}
