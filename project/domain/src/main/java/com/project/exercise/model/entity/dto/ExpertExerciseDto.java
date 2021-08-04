package com.project.exercise.model.entity.dto;

import com.project.exercise.model.entity.enums.ExerciseType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExpertExerciseDto {
    private Long id;
    private String exerciseName;
    private ExerciseType type;
    private String expertURL;
}
