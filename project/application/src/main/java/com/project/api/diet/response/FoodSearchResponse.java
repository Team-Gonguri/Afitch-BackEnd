package com.project.api.diet.response;

import com.project.common.dto.PageDto;
import com.project.diet.model.entity.Food;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@ApiModel("음식 검색 응답")
public class FoodSearchResponse {
    @ApiModelProperty("현제 페이지")
    private int currentPages;
    @ApiModelProperty("전체 페이지")
    private int totalPages;
    @ApiModelProperty("음식들 (p당 5개)")
    private List<Food> foods;

    public FoodSearchResponse(PageDto<Food> dto) {
        this.currentPages = dto.getCurrentPage();
        this.totalPages = dto.getTotalPages();
        this.foods = dto.getContents();
    }

}
