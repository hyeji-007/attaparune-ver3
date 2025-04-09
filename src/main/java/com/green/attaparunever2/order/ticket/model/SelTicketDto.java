package com.green.attaparunever2.order.ticket.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelTicketDto {
    private long ticketId;
    private long orderId;
    private String expiredDate;
    private int ticketStatus;
    private String useDate;
    private String createdAt;
}
