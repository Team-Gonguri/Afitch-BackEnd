package com.project.exercise.exceptions;

public class ExerciseNotExistsException extends RuntimeException {
    public ExerciseNotExistsException() {
        super("운동이 존재하지 않습니다.");
    }
}
