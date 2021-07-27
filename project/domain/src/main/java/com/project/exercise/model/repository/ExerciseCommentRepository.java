package com.project.exercise.model.repository;

import com.project.exercise.model.entity.ExerciseComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseCommentRepository extends JpaRepository<ExerciseComment, Long> {
}
