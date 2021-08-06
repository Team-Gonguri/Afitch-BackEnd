package com.project.exercise.exceptions;

public class ExerciseUserNotExistsException extends RuntimeException {
    public ExerciseUserNotExistsException() {
        super("참여한 운동 정보가 존재하지 않습니다.");
    }
}
