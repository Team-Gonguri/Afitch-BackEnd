package com.project.api.exercise.response;

import com.project.exercise.model.dto.ExerciseCommentDto;
import lombok.Getter;

@Getter
public class ExerciseCommentResponse {
    private Long id;
    private String nickName;
    private String text;
    private String createdAt;

    public ExerciseCommentResponse(ExerciseCommentDto dto) {
        this.id = dto.getId();
        this.nickName = dto.getNickName();
        this.text = dto.getText();
        this.createdAt = dto.getCreatedAt();
    }
}
