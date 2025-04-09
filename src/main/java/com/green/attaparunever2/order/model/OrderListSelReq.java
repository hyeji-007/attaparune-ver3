package com.green.attaparunever2.order.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(title = "식당 주문 조회 응답")
public class OrderListSelReq {
    @Schema(title = "식당 PK")
    private long restaurantId;
}
