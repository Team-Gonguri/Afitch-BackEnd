package com.project.api.diet.controller;

import com.project.api.diet.request.DietSaveRequest;
import com.project.api.diet.response.FoodCategoryResponse;
import com.project.api.diet.response.DietSaveResponse;
import com.project.api.diet.response.FoodSearchResponse;
import com.project.diet.service.DietService;
import com.project.security.AuthInfo;
import com.project.security.Authenticated;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/diet")
@RequiredArgsConstructor
public class DietController {

    private final DietService dietService;

    @GetMapping("/categories")
    @ApiOperation("음식 카테고리 가져오기")
    @ResponseStatus(HttpStatus.OK)
    public FoodCategoryResponse getFoodCategories() {
        return new FoodCategoryResponse(dietService.getFoodCategories());
    }

    @GetMapping("/search")
    @ApiOperation("음식 검색하기")
    @ResponseStatus(HttpStatus.OK)
    public FoodSearchResponse findFood(
            @RequestParam String keyword,
            @RequestParam int page
    ) {
        return new FoodSearchResponse(dietService.findFoods(keyword, page));
    }

    @PostMapping
    @ApiOperation("식단 저장하기")
    @ResponseStatus(HttpStatus.CREATED)
    public DietSaveResponse saveDiet(
            @Authenticated AuthInfo authInfo,
            @RequestBody DietSaveRequest req) {
        return new DietSaveResponse(dietService.saveDiet(authInfo.getId(), req.getFoods(), req.getType()));
    }

}
