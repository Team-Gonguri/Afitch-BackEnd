package com.project.api.exercise.controller;

import com.project.api.exercise.request.UpdateCommentRequest;
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
@RequestMapping("/exercises/{exerciseId}/users/{exerciseUserId}/comment/{commentId}")
public class ExerciseCommentController {

    private final ExerciseCommentService exerciseCommentService;

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("댓글 수정")
    public void updateComment(
            @PathVariable Long exerciseId,
            @PathVariable Long exerciseUserId,
            @PathVariable Long commentId,
            @Valid @RequestBody UpdateCommentRequest req
    ) {
        exerciseCommentService.updateComments(commentId, req.getText());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("댓글 삭제")
    public void deleteComment(
            @Authenticated AuthInfo authInfo,
            @PathVariable Long exerciseId,
            @PathVariable Long exerciseUserId,
            @PathVariable Long commentId
    ) {
        exerciseCommentService.deleteComment(authInfo.getId(), commentId);
    }
}
