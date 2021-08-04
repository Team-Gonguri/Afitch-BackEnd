package com.project.exercise.model.entity.dto;

import com.project.DateUtils;
import com.project.exercise.model.entity.ExerciseUser;
import com.project.exercise.model.entity.enums.ExerciseType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserExerciseDto {
    private Long id;
    private String exerciseName;
    private ExerciseType type;
    private String userURL;
    private String expertURL;
    private String createdAt;

    public UserExerciseDto(ExerciseUser exerciseUser) {
        this.id = exerciseUser.getId();
        this.exerciseName = exerciseUser.getExercise().getName();
        this.expertURL = exerciseUser.getExercise().getUrl();
        this.userURL = exerciseUser.getUrl();
        this.createdAt = DateUtils.parseDateToString(exerciseUser.getCreatedAt());
    }
}
