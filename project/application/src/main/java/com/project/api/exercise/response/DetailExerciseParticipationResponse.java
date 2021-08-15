package com.project.api.exercise.response;

import com.project.exercise.model.dto.ExerciseCommentDto;
import com.project.exercise.model.dto.DetailExerciseParticipationDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;

@Getter
@ApiModel("사용자 운동 영상 상세 정보")
public class DetailExerciseParticipationResponse {
    @ApiModelProperty("사용자 운동 영상 식별자")
    private Long id;
    @ApiModelProperty("사용자 닉네임")
    private String nickName;
    @ApiModelProperty("운동 이름")
    private String exerciseName;
    @ApiModelProperty("사용자 운동 영상 URL")
    private String url;
    @ApiModelProperty("점수")
    private int score;
    @ApiModelProperty("댓글 갯수")
    private int commentNum;
    @ApiModelProperty("댓글들")
    private List<ExerciseCommentDto> comments;

    public DetailExerciseParticipationResponse(DetailExerciseParticipationDto exerciseParticipation, List<ExerciseCommentDto> comments) {
        this.id = exerciseParticipation.getId();
        this.nickName = exerciseParticipation.getNickName();
        this.url = exerciseParticipation.getUserURL();
        this.exerciseName = exerciseParticipation.getExerciseName();
        this.score = exerciseParticipation.getScore();
        this.comments = comments;
        this.commentNum = comments.size();
    }
}
