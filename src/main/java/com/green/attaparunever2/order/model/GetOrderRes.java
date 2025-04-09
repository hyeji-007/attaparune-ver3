package com.green.attaparunever2.order.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(title = "주문 정보")
public class GetOrderRes {
    @Schema(title = "주문 ID")
    private long orderId;

    @Schema(title = "사용자 ID")
    private long userId;

    @Schema(title = "예약 여부")
    private int reservationYn;

    @Schema(title = "예약 상태")
    private int reservationStatus;

    @Schema(title = "주문 날짜")
    private String orderDate;

    @Schema(title = "총 가격")
    private int totalPrice;

    @Schema(title = "예약 ID")
    private long reservationId;

    @Schema(title = "예약 시간")
    private String reservationTime;

    @Schema(title = "예약 인원 수")
    private int reservationPeopleCount;

    @Schema(title = "예약 취소 사유")
    private String reservationCancelReason;

    @Schema(title = "사용자 전화번호")
    private String userPhone;

    @Schema(title = "예약 생성일")
    private String reservationCreatedAt;

    @Schema(title = "주문 메뉴 리스트")
    private List<GetOrderDto> orderDtoList;
}
