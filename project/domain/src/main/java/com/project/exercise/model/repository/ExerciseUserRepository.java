package com.project.exercise.model.repository;

import com.project.exercise.model.entity.ExerciseUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseUserRepository extends JpaRepository<ExerciseUser, Long> {
}
