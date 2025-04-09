package com.green.attaparunever2.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TicketMakeMessageRes {
    private long orderId;
    private long restaurantId;
    private long userId;
    private int reservationYn;
    private int reservationStatus;
    private String createdAt;
    private String typeMessage;
}
