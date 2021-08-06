package com.project.api.exercise.response;

import com.project.exercise.model.dto.ExerciseCommentDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel("댓글 정보")
public class ExerciseCommentResponse {
    @ApiModelProperty("댓글 식별자")
    private Long id;
    @ApiModelProperty("댓글 작성자 닉네임")
    private String nickName;
    @ApiModelProperty("댓글 본문")
    private String text;
    @ApiModelProperty("댓글 작성 시간")
    private String createdAt;

    public ExerciseCommentResponse(ExerciseCommentDto dto) {
        this.id = dto.getId();
        this.nickName = dto.getNickName();
        this.text = dto.getText();
        this.createdAt = dto.getCreatedAt();
    }
}
