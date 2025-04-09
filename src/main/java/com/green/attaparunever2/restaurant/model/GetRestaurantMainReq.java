package com.green.attaparunever2.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.attaparunever2.common.model.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "사용자 메인 화면 식당 리스트 요청")
public class GetRestaurantMainReq extends Paging {
    @Schema(description = "식당 카테고리 PK(1:한식, 2:중식, 3:일식)", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @Max(value = 3, message = "존재하지 않는 기준 입니다.")
    @Min(value = 1, message = "존재하지 않는 기준 입니다.")
    private long categoryId;

    @Min(value = 0, message = "filterType은 0 이상이어야 합니다.")
    @Max(value = 1, message = "filterType은 1 이하이어야 합니다.")
    @Schema(description = "정렬 필터(null: 기본순, 0: 평균 별점 순, 1: 리뷰 순")
    private Integer filterType;

    private Long userId;

    public GetRestaurantMainReq(Integer page, Integer size, long categoryId, Integer filterType) {
        super(page, size);
        this.categoryId = categoryId;
        this.filterType = filterType;
    }
}