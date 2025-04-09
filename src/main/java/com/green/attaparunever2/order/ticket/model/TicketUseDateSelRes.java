package com.green.attaparunever2.order.ticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketUseDateSelRes {
    @Schema(title = "티켓 PK", example = "1")
    private long ticketId;
    @Schema(title = "식권 사용 일자", example = "2025-02-05 14:32:15", description = "식권이 사용된 시점")
    private String useDate;
}
