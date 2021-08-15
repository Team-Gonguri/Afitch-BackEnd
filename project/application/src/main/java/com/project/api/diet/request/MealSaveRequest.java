package com.project.api.diet.request;

import com.project.diet.model.dto.FoodWrapperDto;
import com.project.diet.model.entity.enums.MealType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("식사 저장 요청")
public class MealSaveRequest {
    @NotNull
    @ApiModelProperty("식사 타입 (BREAKFAST | LUNCH | DINNER)")
    private MealType type;

    @NotEmpty
    @ApiModelProperty("음식 정보")
    private List<FoodWrapperDto> foods;

    @NotEmpty
    @ApiModelProperty("저장하길 원하는 날짜 (yyyyMMdd)")
    private String date;
}
