package com.green.attaparunever2.order.ticket.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketUpdReq {
    @Schema(description = "식권 pk", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long ticketId;

    @Schema(description = "식당 pk", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long restaurantId;

    @Schema(description = "간편결제 비밀번호", example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    private String paymentPassword;
}
