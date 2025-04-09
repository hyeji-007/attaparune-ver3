package com.green.attaparunever2.restaurant.model;

import com.green.attaparunever2.common.model.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetRestaurantReviewReq extends Paging {
    @Schema(description = "식당 pk")
    private Long restaurantId;
    @Schema(description = "시작일")
    private String startDate;
    @Schema(description = "종료일")
    private String endDate;

    public GetRestaurantReviewReq(Integer page, Integer size) {
        super(page, size);
    }
}
