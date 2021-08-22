package com.project.exception;

public class InvalidFileTypeException extends RuntimeException{
    public InvalidFileTypeException(){
        super("비디오 타입의 파일 형식이 아닙니다.");
    }
}
