package com.project.exercise.model.dto;

import com.project.exercise.model.entity.Exercise;
import com.project.exercise.model.entity.enums.ExerciseCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DetailExerciseDto {
    private Long id;
    private String name;
    private ExerciseCategory type;
    private String url;

    public DetailExerciseDto(Exercise exercise){
        this.id = exercise.getId();
        this.name = exercise.getName();
        this.type =  exercise.getType();
        this.url = exercise.getUrl();
    }
}
