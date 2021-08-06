package com.project.exercise.model.repository;

import com.project.exercise.model.entity.ExerciseComment;
import com.project.exercise.model.entity.ExerciseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseCommentRepository extends JpaRepository<ExerciseComment, Long> {
    List<ExerciseComment> findAllByExerciseUser(ExerciseUser exerciseUser);
}
