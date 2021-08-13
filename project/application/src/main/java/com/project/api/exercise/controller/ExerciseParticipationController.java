package com.project.api.exercise.controller;

import com.project.api.exercise.response.DetailExerciseUserResponse;
import com.project.api.exercise.response.SaveExerciseResponse;
import com.project.api.exercise.response.SimpleExerciseUserListResponse;
import com.project.exercise.model.entity.enums.OrderType;
import com.project.exercise.service.ExerciseCommentService;
import com.project.exercise.service.ExerciseParticipationService;
import com.project.security.AuthInfo;
import com.project.security.Authenticated;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercises/{exerciseId}/participation")
public class ExerciseParticipationController {

    private final ExerciseParticipationService exerciseParticipationService;
    private final ExerciseCommentService exerciseCommentService;

    @GetMapping("/{participationId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("운동에 참여한 사용자 상세 정보 보기")
    public DetailExerciseUserResponse getDetailExerciseUser(
            @Authenticated AuthInfo authInfo,
            @PathVariable Long exerciseId,
            @PathVariable Long participationId
    ) {
        return new DetailExerciseUserResponse(
                exerciseParticipationService.getDetailExerciseUser(authInfo.getId(), participationId),
                exerciseCommentService.getComments(participationId)
        );
    }


    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("운동 별 사용자 참여리스트 가져오기")
    public SimpleExerciseUserListResponse getSimpleExerciseUserList(
            @PathVariable Long exerciseId,
            @RequestParam OrderType order
    ) {
        return new SimpleExerciseUserListResponse(
                exerciseParticipationService.getExerciseUserList(exerciseId, order)
        );
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("사용자 운동 영상 저장")
    public SaveExerciseResponse saveExerciseUserVideo(
            @Authenticated AuthInfo authInfo,
            @RequestPart MultipartFile video,
            @PathVariable Long exerciseId,
            @RequestParam String open
    ) throws IOException {
        return new SaveExerciseResponse(
                exerciseParticipationService.saveExerciseUserVideo(authInfo.getId(), exerciseId, video, open)
        );
    }

    @DeleteMapping("/{exerciseUserId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("사용자 운동 영상 삭제")
    public void deleteExerciseUserVideo(
            @Authenticated AuthInfo authInfo,
            @PathVariable Long exerciseId,
            @PathVariable Long exerciseUserId) {

        exerciseParticipationService.deleteExerciseUserVideo(authInfo.getId(), exerciseUserId);
    }
}
