package com.project.api.exercise.exception;

import com.project.exception.ErrorResponse;
import com.project.exercise.exceptions.ExerciseCommentNotExistsException;
import com.project.exercise.exceptions.ExerciseNotExistsException;
import com.project.exercise.exceptions.ExerciseUserNotExistsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExerciseExceptionHandler {

    @ExceptionHandler(ExerciseNotExistsException.class)
    public ErrorResponse handleExerciseNotExistException(Exception e) {
        return new ErrorResponse(ExerciseExceptionType.EXERCISE_NOT_FOUND.getMessage(), ExerciseExceptionType.EXERCISE_NOT_FOUND.getCode());
    }

    @ExceptionHandler(ExerciseUserNotExistsException.class)
    public ErrorResponse handleExerciseUserNotExistException(Exception e) {
        return new ErrorResponse(ExerciseExceptionType.EXERCISE_USER_NOT_FOUND.getMessage(), ExerciseExceptionType.EXERCISE_USER_NOT_FOUND.getCode());
    }

    @ExceptionHandler(ExerciseCommentNotExistsException.class)
    public ErrorResponse handleExerciseCommentNotExistException(Exception e) {
        return new ErrorResponse(ExerciseExceptionType.EXERCISE_COMMENT_NOT_FOUND.getMessage(), ExerciseExceptionType.EXERCISE_COMMENT_NOT_FOUND.getCode());
    }

}
