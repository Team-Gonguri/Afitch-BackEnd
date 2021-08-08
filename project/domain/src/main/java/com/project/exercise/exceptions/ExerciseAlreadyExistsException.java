package com.project.exercise.exceptions;

public class ExerciseAlreadyExistsException extends RuntimeException {
    public ExerciseAlreadyExistsException() {
        super("이미 존재하는 운동입니다.");
    }
}
