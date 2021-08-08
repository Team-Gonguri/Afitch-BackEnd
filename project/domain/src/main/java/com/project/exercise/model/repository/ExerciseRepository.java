package com.project.exercise.model.repository;

import com.project.exercise.model.entity.Exercise;
import com.project.exercise.model.entity.enums.ExerciseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findAllByCategory(ExerciseCategory category);
}
