package com.project.exercise.model.dto;

import com.project.exercise.model.entity.ExerciseUser;
import com.project.exercise.model.entity.enums.PublicScope;
import lombok.Getter;

@Getter
public class SimpleExerciseUserDto {
    private Long id;
    private String userName;
    private int score;
    private PublicScope scope;

    public SimpleExerciseUserDto(ExerciseUser exerciseUser) {
        this.id = exerciseUser.getId();
        this.userName = exerciseUser.getUser().getNickName();
        this.score = exerciseUser.getScore();
        this.scope = exerciseUser.getScope();
    }
}
