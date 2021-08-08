package com.project.api.admin.controller;

import com.project.api.admin.request.RegisterExerciseRequest;
import com.project.api.admin.response.RegisterExerciseResponse;
import com.project.exercise.service.ExerciseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ExerciseService exerciseService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/exercise", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiOperation("운동 저장")
    public RegisterExerciseResponse saveExercise(
            @RequestPart MultipartFile video,
            @Valid @RequestPart RegisterExerciseRequest req
    ) throws IOException {
        return new RegisterExerciseResponse(
                exerciseService.saveExercise(req.getName(), req.getCategory(), video)
        );
    }
}
