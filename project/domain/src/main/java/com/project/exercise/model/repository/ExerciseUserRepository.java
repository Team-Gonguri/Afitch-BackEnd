package com.project.exercise.model.repository;

import com.project.auth.model.entity.User;
import com.project.exercise.model.entity.Exercise;
import com.project.exercise.model.entity.ExerciseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface ExerciseUserRepository extends JpaRepository<ExerciseUser, Long> {
    Optional<ExerciseUser> findByExerciseAndUserAndCreatedAt(Exercise exercise, User user, Date createdAt);
}
