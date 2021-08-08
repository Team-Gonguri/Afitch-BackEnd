package com.project.api.exercise.controller;

import com.project.api.exercise.request.ExerciseCommentRequest;
import com.project.api.exercise.response.ExerciseCommentResponse;
import com.project.exercise.service.ExerciseCommentService;
import com.project.security.AuthInfo;
import com.project.security.Authenticated;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercises/{exerciseId}/participation/{participationId}/comment")
public class ExerciseCommentController {

    private final ExerciseCommentService exerciseCommentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("댓글 작성")
    public ExerciseCommentResponse saveComment(
            @Authenticated AuthInfo authInfo,
            @PathVariable Long exerciseId,
            @PathVariable Long participationId,
            @Valid @RequestBody ExerciseCommentRequest req) {
        return new ExerciseCommentResponse(
                exerciseCommentService.saveComment(authInfo.getId(), participationId, req.getText())
        );
    }

    @PutMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("댓글 수정")
    public ExerciseCommentResponse updateComment(
            @Authenticated AuthInfo authInfo,
            @PathVariable Long exerciseId,
            @PathVariable Long participationId,
            @PathVariable Long commentId,
            @Valid @RequestBody ExerciseCommentRequest req
    ) {
        return new ExerciseCommentResponse(exerciseCommentService.updateComment(authInfo.getId(), commentId, req.getText()));
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("댓글 삭제")
    public void deleteComment(
            @Authenticated AuthInfo authInfo,
            @PathVariable Long exerciseId,
            @PathVariable Long participationId,
            @PathVariable Long commentId
    ) {
        exerciseCommentService.deleteComment(authInfo.getId(), commentId);
    }
}
