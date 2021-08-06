package com.project.api.exercise.controller;

import com.project.api.exercise.response.DetailExerciseUserResponse;
import com.project.api.exercise.response.SaveExerciseResponse;
import com.project.api.exercise.response.SimpleExerciseUserListResponse;
import com.project.exercise.model.entity.enums.OrderType;
import com.project.exercise.service.ExerciseCommentService;
import com.project.exercise.service.ExerciseUserService;
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
@RequestMapping("/exercises/{exerciseId}/users")
public class ExerciseUserController {

    private final ExerciseUserService exerciseUserService;
    private final ExerciseCommentService exerciseCommentService;

    @GetMapping("/{exerciseUserId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("운동에 참여한 사용자 상세 정보 보기")
    public DetailExerciseUserResponse getDetailExerciseUser(
            @Authenticated AuthInfo authInfo,
            @PathVariable Long exerciseId,
            @PathVariable Long exerciseUserId
    ) {
        return new DetailExerciseUserResponse(
                exerciseUserService.getDetailExerciseUser(authInfo.getId(), exerciseUserId),
                exerciseCommentService.getComments(exerciseUserId)
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
                exerciseUserService.getExerciseUserList(exerciseId, order)
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
                exerciseUserService.saveExerciseUserVideo(authInfo.getId(), exerciseId, video, open)
        );
    }

    @DeleteMapping("/{exerciseUserId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("사용자 운동 영상 삭제")
    public void deleteExerciseUserVideo(
            @Authenticated AuthInfo authInfo,
            @PathVariable Long exerciseId,
            @PathVariable Long exerciseUserId) {

        exerciseUserService.deleteExerciseUserVideo(authInfo.getId(), exerciseUserId);
    }
}
