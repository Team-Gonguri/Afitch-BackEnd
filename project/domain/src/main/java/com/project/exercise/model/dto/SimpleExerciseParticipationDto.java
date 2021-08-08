package com.project.exercise.model.dto;

import com.project.exercise.model.entity.ExerciseParticipation;
import com.project.exercise.model.entity.enums.PublicScope;
import lombok.Getter;

@Getter
public class SimpleExerciseParticipationDto {
    private Long id;
    private String userName;
    private int score;
    private PublicScope scope;

    public SimpleExerciseParticipationDto(ExerciseParticipation exerciseParticipation) {
        this.id = exerciseParticipation.getId();
        this.userName = exerciseParticipation.getUser().getNickName();
        this.score = exerciseParticipation.getScore();
        this.scope = exerciseParticipation.getScope();
    }
}
