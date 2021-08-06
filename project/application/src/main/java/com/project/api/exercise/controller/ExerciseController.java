package com.project.api.exercise.controller;

import com.project.api.exercise.response.DetailExerciseResponse;
import com.project.api.exercise.response.ExerciseCategoriesResponse;
import com.project.api.exercise.response.SimpleExerciseListResponse;
import com.project.exercise.model.entity.enums.ExerciseCategory;
import com.project.exercise.service.ExerciseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("운동 카테고리 가져오기")
    public ExerciseCategoriesResponse getExerciseCategory() {
        return new ExerciseCategoriesResponse(exerciseService.getExerciseCategory());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("카테고리별 간단한 운동정보들 가져오기")
    public SimpleExerciseListResponse getExercisesByCategory(@RequestParam ExerciseCategory category) {
        return new SimpleExerciseListResponse(
                exerciseService.getExercises(category)
        );
    }

    @GetMapping("/{exerciseId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("운동 세부정보 가져오기")
    public DetailExerciseResponse getExpertVideo(
            @PathVariable Long exerciseId
    ) {
        return new DetailExerciseResponse(
                exerciseService.getExpertExerciseVideo(exerciseId)
        );
    }
}
