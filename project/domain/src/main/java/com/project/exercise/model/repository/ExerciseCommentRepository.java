package com.project.exercise.model.repository;

import com.project.exercise.model.entity.ExerciseComment;
import com.project.exercise.model.entity.ExerciseParticipation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseCommentRepository extends JpaRepository<ExerciseComment, Long> {
    List<ExerciseComment> findAllByExerciseParticipation(ExerciseParticipation exerciseParticipation);
}
