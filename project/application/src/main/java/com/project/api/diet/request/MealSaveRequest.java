package com.project.api.diet.request;

import com.project.diet.model.dto.FoodWrapperDto;
import com.project.diet.model.entity.enums.MealType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Getter
@AllArgsConstructor
@ApiModel("식사 저장 요청")
public class MealSaveRequest {
    @NotEmpty
    @ApiModelProperty("식사 타입 (BREAKFAST | LUNCH | DINNER)")
    private MealType type;

    @NotEmpty
    @ApiModelProperty("음식 정보")
    private List<FoodWrapperDto> foods;

    @NotEmpty
    @ApiModelProperty("저장하길 원하는 날짜 (yyyyMMdd)")
    private String date;
}
