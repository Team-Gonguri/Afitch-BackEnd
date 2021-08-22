package com.project.exercise.model.repository;

import com.project.auth.model.entity.User;
import com.project.exercise.model.entity.Exercise;
import com.project.exercise.model.entity.ExerciseParticipation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ExerciseParticipationRepository extends JpaRepository<ExerciseParticipation, Long> {
    Optional<ExerciseParticipation> findByExerciseAndUserAndCreatedAt(Exercise exercise, User user, Date createdAt);

    List<ExerciseParticipation> findAllByExerciseOrderByIdDesc(Exercise exercise);

    List<ExerciseParticipation> findAllByExerciseOrderByScoreDesc(Exercise exercise);

    List<ExerciseParticipation> findTop4ByUserAndCreatedAtOrderByScoreDesc(User user, Date createdAt);

    List<ExerciseParticipation> findByUserAndCreatedAt(User user, Date createdAt);
}
