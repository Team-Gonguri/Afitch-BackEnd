package com.project.exercise.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VectorDto {
    private Coordinate leftShoulder;
    private Coordinate rightShoulder;
    private Coordinate leftElbow;
    private Coordinate rightElbow;
    private Coordinate leftWrist;
    private Coordinate rightWrist;
    private Coordinate leftHip;
    private Coordinate rightHip;
    private Coordinate leftKnee;
    private Coordinate rightKnee;
    private Coordinate leftAnkle;
    private Coordinate rightAnkle;

}
