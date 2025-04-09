package com.green.attaparunever2.restaurant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "매장 대시보드 정보 요청 데이터")
public class SelRestaurantDashboardReq {
    @Schema(description = "식당 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long restaurantId;
    @Schema(description = "오늘 일자", example = "", requiredMode = Schema.RequiredMode.REQUIRED)
    private String date;
}
