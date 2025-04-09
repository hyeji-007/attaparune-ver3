package com.green.attaparunever2.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class ReservationMessageRes {
    private long reservationId; // 예약 후 변경 없을 시 10분 뒤에 자동 취소 처리를 위해 필요.
    private long orderId;
    private long restaurantId;
    private long userId;
    private LocalDateTime reservationTime;
    private int reservationPeopleCount;
    private String userPhone;
    private List<ReservationMenuDto> menuList;
    private String typeMessage;
}
