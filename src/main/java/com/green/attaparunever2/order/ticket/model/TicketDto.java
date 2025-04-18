package com.green.attaparunever2.order.ticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(title = "식권 정보")
public class TicketDto {
    private long restaurantId;
    private String restaurantName;
    private int menuCount;
    private int totalAmount;
    private int personCount;
    private String reservationTime;
    private String menuNames;
    private int ticketStatus;
}
