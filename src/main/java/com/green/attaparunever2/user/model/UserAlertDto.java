package com.green.attaparunever2.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserAlertDto {
    private Long reservationId;
    private String reservationTime;
    private Integer reservationPeopleCount;
    private Integer reservationStatus;
    private Long restaurantId;
    private String restaurantName;
    private Long orderId;
    private Long orderUserId;
    private String orderUserName;
    private Integer approvalStatus;
    private Integer point;
    private String paymentCreatedAt;
    private String message;
}
