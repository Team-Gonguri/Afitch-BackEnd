package com.project.exercise.model.dto;

import com.project.exercise.model.entity.ExerciseComment;
import com.project.utils.DateUtils;
import lombok.Getter;

@Getter
public class ExerciseCommentDto {
    private Long id;
    private String nickName;
    private String text;
    private String createdAt;

    public ExerciseCommentDto(ExerciseComment exerciseComment) {
        this.id = exerciseComment.getId();
        this.nickName = exerciseComment.getUser().getNickName();
        this.text = exerciseComment.getText();
        this.createdAt = DateUtils.parseDateToDetailString(exerciseComment.getCreatedAt());
    }
}
