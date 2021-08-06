package com.project.exercise.exceptions;

public class ExerciseCommentNotExistsException extends RuntimeException{
    public ExerciseCommentNotExistsException(){
        super("해당하는 댓글이 존재하지 않습니다.");
    }
}
