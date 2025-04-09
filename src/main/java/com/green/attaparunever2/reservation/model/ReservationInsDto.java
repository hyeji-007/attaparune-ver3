package com.green.attaparunever2.reservation.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReservationInsDto {
    private long reservationId; // 예약 후 변경 없을 시 10분 뒤에 자동 취소 처리를 위해 필요.
    private long orderId;
    private LocalDateTime reservationTime;
    private int reservationPeopleCount;
    private String userPhone;
}
