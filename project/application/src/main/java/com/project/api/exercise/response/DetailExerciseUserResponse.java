package com.project.api.exercise.response;

import com.project.exercise.model.dto.ExerciseCommentDto;
import com.project.exercise.model.dto.ExerciseUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class DetailExerciseUserResponse {
    private Long id;
    private String nickName;
    private String exerciseName;
    private int score;
    private int commentNum;
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
