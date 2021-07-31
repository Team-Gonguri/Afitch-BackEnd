package com.project.diet.model.repository;

import com.project.auth.model.entity.User;
import com.project.diet.model.entity.Meal;
import com.project.diet.model.entity.enums.MealType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByUserAndCreatedAtOrderByTypeAsc(User user, Date date);

    Meal findByUserAndTypeAndCreatedAt(User user, MealType type, Date date);

    List<Meal> findAllByUser(User user, Pageable pageable);
}
