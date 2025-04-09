package com.green.attaparunever2.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SelUserOrderPastCheckRes {
    @Schema(description = "레스토랑 PK")
    private long restaurantId;

    @Schema(description = "주문 PK")
    private long orderId;

    @Schema(description = "레스토랑 이름")
    private String restaurantName;

    @Schema(description = "예약한 일자")
    private String createdAt;

    @Schema(description = "레스토랑 현장/예약")
    private int reservationYn;

    @Schema(description = "메뉴 총 가격")
    private int menuTotalPrice;

    @Schema(description = "레스토랑 사진")
    private String pic;

    @Schema(description = "리뷰 상태 0 : 없음 1 : 존재")
    private int reviewStatus;

    @Schema(description = "24시간 초과 여부 0 : 안지남 1 : 지남")
    private int is24hourLeft;

    private List<SelUserOrderPastCheckDto> pastDtoList;

}
