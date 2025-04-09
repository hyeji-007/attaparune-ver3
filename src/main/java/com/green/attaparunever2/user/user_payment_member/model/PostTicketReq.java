package com.green.attaparunever2.user.user_payment_member.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostTicketReq {
    @JsonIgnore
    private Long ticketId;

    @Schema(example = "1")
    private long orderId;
}
