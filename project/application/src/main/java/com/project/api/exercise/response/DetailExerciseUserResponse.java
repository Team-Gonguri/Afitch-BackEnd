package com.project.api.exercise.response;

import com.project.exercise.model.dto.ExerciseCommentDto;
import com.project.exercise.model.dto.ExerciseUserDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@ApiModel("사용자 운동 영상 상세 정보")
public class DetailExerciseUserResponse {
    @ApiModelProperty("사용자 운동 영상 식별자")
    private Long id;
    @ApiModelProperty("사용자 닉네임")
    private String nickName;
    @ApiModelProperty("운동 이름")
    private String exerciseName;
    @ApiModelProperty("점수")
    private int score;
    @ApiModelProperty("댓글 갯수")
    private int commentNum;
    @ApiModelProperty("댓글들")
    private List<ExerciseCommentDto> comments;

    public DetailExerciseUserResponse(ExerciseUserDto exerciseUsers, List<ExerciseCommentDto> comments) {
        this.id = exerciseUsers.getId();
        this.nickName = exerciseUsers.getNickName();
        this.exerciseName = exerciseUsers.getExerciseName();
        this.score = exerciseUsers.getScore();
        this.comments = comments;
        this.commentNum = comments.size();
    }
}
