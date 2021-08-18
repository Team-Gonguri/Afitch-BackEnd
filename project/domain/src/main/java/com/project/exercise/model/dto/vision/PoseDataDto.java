package com.project.exercise.model.dto.vision;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PoseDataDto {
    private double score;
    private VectorDto[] keypoints;
}
