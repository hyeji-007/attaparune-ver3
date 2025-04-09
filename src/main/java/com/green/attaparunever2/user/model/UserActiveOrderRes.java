package com.green.attaparunever2.user.model;

import com.green.attaparunever2.order.model.GetOrderDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserActiveOrderRes {
        @Schema(description = "주문 PK")
        private Long orderId;

        @Schema(description = "식당 PK")
        private Long restaurantId;

        @Schema(description = "레스토랑 이름")
        private String restaurantName;

        @Schema(description = "카테고리 명")
        private String categoryName;

        @Schema(description = "주문 일시")
        private LocalDateTime orderDate;

        @Schema(description = "주문 유저 PK")
        private Long orderUserId;

        @Schema(description = "주문 유저 이름")
        private String orderUserName;

        @Schema(description = "주문 유저 핸드폰")
        private String orderUserPhone;

        @Schema(description = "예약자 유저 PK")
        private Long reservationUserId;

        @Schema(description = "예약자 유저 이름")
        private String reservationUserName;

        @Schema(description = "예약자 전화번호")
        private String reservationUserPhone;

        @Schema(description = "예약 시간")
        private LocalDateTime reservationTime;

        @Schema(description = "식권 PK")
        private Long ticketId;

        @Schema(description = "예약 여부")
        private Integer reservationYn;

        @Schema(description = "예약 상태")
        private Integer reservationStatus;

        @Schema(description = "메뉴 정보")
        private List<GetOrderDto> orderDetails;

        @Schema(description = "메뉴 총 가격")
        private int totalMenuCost;

        @Schema(description = "예약 인원 수")
        private int reservationPeopleCount;
}
