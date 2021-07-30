package com.project.diet.model.repository;

import com.project.auth.model.entity.User;
import com.project.diet.model.entity.Meal;
import com.project.diet.model.entity.enums.MealType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {
    public List<Meal> findByUserAndCreatedAtOrderByTypeAsc(User user, Date date);

    public Meal findByUserAndTypeAndCreatedAt(User user, MealType type, Date date);
}
