package com.green.attaparunever2.order.ticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Schema(title = "식권 생성 요청")
@ToString
public class TicketPostReq {
    @JsonIgnore
    private long ticketId;

    @Schema(title = "주문 PK", requiredMode = Schema.RequiredMode.REQUIRED)
    private long orderId;

    @Schema(title = "사용자 포인트")
    private int point;

    @Schema(title = "식권 생성일")
    private String createdAt;
}
