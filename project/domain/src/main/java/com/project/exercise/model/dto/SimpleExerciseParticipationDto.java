package com.project.exercise.model.dto;

import com.project.exercise.model.entity.ExerciseParticipation;
import com.project.exercise.model.entity.enums.PublicScope;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SimpleExerciseParticipationDto {
    private Long participationId;
    private Long exerciseId;
    private String exerciseName;
    private String userName;
    private double score;
    private PublicScope scope;

    public SimpleExerciseParticipationDto(ExerciseParticipation exerciseParticipation) {
        this.participationId = exerciseParticipation.getId();
        this.exerciseId = exerciseParticipation.getExercise().getId();
        this.exerciseName = exerciseParticipation.getExercise().getName();
        this.userName = exerciseParticipation.getUser().getNickName();
        this.score = exerciseParticipation.getScore();
        this.scope = exerciseParticipation.getScope();
    }
}
