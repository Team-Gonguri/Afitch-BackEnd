package com.project.api.diet.controller;

import com.project.api.diet.request.MealSaveRequest;
import com.project.api.diet.response.DietSearchResponse;
import com.project.api.diet.response.MealDetailResponse;
import com.project.diet.service.DietService;
import com.project.security.AuthInfo;
import com.project.security.Authenticated;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;


@RestController
@RequestMapping("/diet")
@RequiredArgsConstructor
public class DietController {

    private final DietService dietService;

    @GetMapping
    @ApiOperation("식단 가져오기")
    @ResponseStatus(HttpStatus.OK)
    public DietSearchResponse getDiet(
            @Authenticated AuthInfo authInfo,
            @RequestParam String date
    ) throws ParseException {
        return new DietSearchResponse(dietService.getDiet(authInfo.getId(), date));
    }

    @GetMapping("/meal/{mealId}")
    @ApiOperation("식사 가져오기")
    @ResponseStatus(HttpStatus.OK)
    public MealDetailResponse getMeal(
            @Authenticated AuthInfo authInfo,
            @PathVariable Long mealId
    ) {
        return new MealDetailResponse(dietService.getMeal(authInfo.getId(), mealId));
    }

    @PostMapping("/meal")
    @ApiOperation("식단 저장하기(이미 존재하면 수정)")
    @ResponseStatus(HttpStatus.CREATED)
    public MealDetailResponse saveDiet(
            @Authenticated AuthInfo authInfo,
            @Valid @RequestBody MealSaveRequest req) throws ParseException {
        return new MealDetailResponse(dietService.saveMeal(authInfo.getId(), req.getFoods(), req.getType(), req.getDate()));
    }


}
