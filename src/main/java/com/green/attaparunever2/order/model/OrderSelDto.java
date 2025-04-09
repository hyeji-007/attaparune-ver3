package com.green.attaparunever2.order.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderSelDto {
    private long orderId;
    private long restaurantId;
    private long userId;
    private int reservationYn;
    private int reservationStatus;
    private String createdAt;
}
