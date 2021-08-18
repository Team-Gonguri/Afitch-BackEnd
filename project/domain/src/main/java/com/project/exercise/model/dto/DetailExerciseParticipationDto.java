package com.project.exercise.model.dto;

import com.project.exercise.model.entity.ExerciseParticipation;
import com.project.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DetailExerciseParticipationDto {
    private Long id;
    private String nickName;
    private String exerciseName;
    private double score;
    private String userURL;
    private String expertURL;
    private String createdAt;

    public DetailExerciseParticipationDto(ExerciseParticipation exerciseParticipation) {
        this.id = exerciseParticipation.getId();
        this.nickName = exerciseParticipation.getUser().getNickName();
        this.score = exerciseParticipation.getScore();
        this.exerciseName = exerciseParticipation.getExercise().getName();
        this.expertURL = exerciseParticipation.getExercise().getUrl();
        this.userURL = exerciseParticipation.getUrl();
        this.createdAt = DateUtils.parseDateToSimpleString(exerciseParticipation.getCreatedAt());
    }

    public DetailExerciseParticipationDto(ExerciseParticipation exerciseParticipation, String url) {
        this.id = exerciseParticipation.getId();
        this.nickName = exerciseParticipation.getUser().getNickName();
        this.score = exerciseParticipation.getScore();
        this.exerciseName = exerciseParticipation.getExercise().getName();
        this.expertURL = exerciseParticipation.getExercise().getUrl();
        this.userURL = url;
        this.createdAt = DateUtils.parseDateToSimpleString(exerciseParticipation.getCreatedAt());
    }
}
