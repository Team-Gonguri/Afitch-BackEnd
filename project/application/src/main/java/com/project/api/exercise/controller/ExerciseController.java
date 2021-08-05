package com.project.api.exercise.controller;

import com.project.api.exercise.response.GetExpertVideoResponse;
import com.project.api.exercise.response.SaveExerciseResponse;
import com.project.exercise.service.ExerciseService;
import com.project.security.AuthInfo;
import com.project.security.Authenticated;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/expert/{exerciseId}")
    @ApiOperation("트레이너 운동 영상 가져오기")
    public GetExpertVideoResponse getExpertVideo(
            @PathVariable Long exerciseId
    ) {
        return new GetExpertVideoResponse(
                exerciseService.getExpertExerciseVideo(exerciseId)
        );
    }


    @PostMapping(value = "/{exerciseId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation("운동 영상 저장")
    public SaveExerciseResponse saveUserExerciseVideo(
            @Authenticated AuthInfo authInfo,
            @RequestPart MultipartFile video,
            @PathVariable Long exerciseId,
            @RequestParam String open
    ) throws ParseException, IOException {
        return new SaveExerciseResponse(
                exerciseService.saveUserExerciseVideo(authInfo.getId(), exerciseId, video, open)
        );
    }
}
