package com.project.exercise.model.dto;

import com.project.exercise.model.entity.Exercise;
import lombok.Getter;

@Getter
public class SimpleExerciseDto {
    private Long id;
    private String name;

    public SimpleExerciseDto(Exercise exercise) {
        this.id = exercise.getId();
        this.name = exercise.getName();
    }
}
