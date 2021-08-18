package com.project.exercise.model.dto.vision;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PoseDataDto {
    private double score;
    private VectorDto[] keypoints;
}
