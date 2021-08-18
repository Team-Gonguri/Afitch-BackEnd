package com.project.exercise.model.dto.vision;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VisionBodyDto {
    private PoseDataDto[] data;
}
