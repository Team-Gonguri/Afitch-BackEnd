package com.project.api.exercise.exception;

import lombok.Getter;

@Getter
public enum ExerciseExceptionType {

    EXERCISE_NOT_FOUND("EXERCISE-001","운동을 찾을 수 없습니다."),
    EXERCISE_USER_NOT_FOUND("EXERCISE-002","사용자 운동영상을 찾을 수 없습니다."),
    EXERCISE_COMMENT_NOT_FOUND("EXERCISE-003","해당하는 댓글을 찾을 수 없습니다.");

    private final String code;
    private final String message;


    ExerciseExceptionType(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
