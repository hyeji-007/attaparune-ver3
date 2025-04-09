package com.green.attaparunever2.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(title = "주문 조회")
public class GetUserOrderVer2Res {
    @Schema(title = "주문 PK")
    private long orderId;
    @Schema(title = "식당 이름")
    private String restaurantName;
    @Schema(title = " 식당 카테고리 이름", example = "한식, 중식")
    private String categoryName;
    @Schema(title = "오더 상세 주문시간")
    private String createdAt;
    @Schema(title = "예약 인원")
    private int reservationPeopleCount;
    @Schema(title = "예약 시간")
    private String reservationTime;
    @Schema(title = "사용자 이름")
    private String userName;
    @Schema(title = "사용자 연락처")
    private String userPhone;
    @Schema(title = "모든 주문 메뉴의 총 가격")
    private int menuTotalPrice;
    @Schema(title = "예약 여부", description = "0:미예약, 1:예약")
    private int reservationYn;
    @Schema(title = "주문 메뉴 리스트")
    private List<GetUserOrderMenuListDto> menuList;
}
