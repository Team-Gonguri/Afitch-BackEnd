package com.project.exercise.model.dto.vision;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VectorDto {
    private double score;
    private String part;
    private Coordinate position;
}
