package com.project.exercise.model.dto;

import com.project.exercise.model.entity.ExerciseUser;
import com.project.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExerciseUserDto {
    private Long id;
    private String nickName;
    private String exerciseName;
    private int score;
    private String userURL;
    private String expertURL;
    private String createdAt;

    public ExerciseUserDto(ExerciseUser exerciseUser) {
        this.id = exerciseUser.getId();
        this.nickName = exerciseUser.getUser().getNickName();
        this.score = exerciseUser.getScore();
        this.exerciseName = exerciseUser.getExercise().getName();
        this.expertURL = exerciseUser.getExercise().getUrl();
        this.userURL = exerciseUser.getUrl();
        this.createdAt = DateUtils.parseDateToSimpleString(exerciseUser.getCreatedAt());
    }

    public ExerciseUserDto(ExerciseUser exerciseUser, String url) {
        this.id = exerciseUser.getId();
        this.nickName = exerciseUser.getUser().getNickName();
        this.score = exerciseUser.getScore();
        this.exerciseName = exerciseUser.getExercise().getName();
        this.expertURL = exerciseUser.getExercise().getUrl();
        this.userURL = url;
        this.createdAt = DateUtils.parseDateToSimpleString(exerciseUser.getCreatedAt());
    }
}
