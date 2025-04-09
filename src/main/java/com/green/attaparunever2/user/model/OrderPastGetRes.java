package com.green.attaparunever2.user.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class OrderPastGetRes {
    @Schema(description = "식당 PK")
    private long restaurantId;
    @Schema(description = "식당 이름")
    private String restaurantName;
    @Schema(description = "식당 사진")
    private String filePath;
    @Schema(description = "현장/예약 여부")
    private int reservationYn;
    @Schema(description = "메뉴이름")
    private String menuName;
    @Schema(description = "메뉴 총 가격")
    private int menuTotalPrice;
    @Schema(description = "메뉴 갯수")
    private int menuCount;
    @Schema(description = "예약한 일자")
    private String createdAt;
    @Schema(description = "식권 PK")
    private Long ticketId;
}
