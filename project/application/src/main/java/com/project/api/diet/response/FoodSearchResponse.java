package com.project.api.diet.response;

import com.project.common.dto.PageDto;
import com.project.diet.model.entity.Food;
import lombok.Getter;

import java.util.List;

@Getter
public class FoodSearchResponse {
    private int currentPages;
    private int totalPages;
    private List<Food> foods;

    public FoodSearchResponse(PageDto<Food> dto) {
        this.currentPages = dto.getCurrentPage();
        this.totalPages = dto.getTotalPages();
        this.foods = dto.getContents();
    }

}
