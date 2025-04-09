package com.green.attaparunever2.user.user_payment_member.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPaymentAmountPatchReq {

    @Schema(title = "주문 Id")
    private long orderId;

    @Schema(title = "유저 Id")
    private long userId;

    @NotNull
    @Schema(title = "승인 포인트", requiredMode = Schema.RequiredMode.REQUIRED)
    private int point;
}
