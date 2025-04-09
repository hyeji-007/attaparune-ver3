package com.green.attaparunever2.admin.restaurant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdPaymentPasswordReq {
    @Schema(description = "관리자 PK", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private long adminId;

    @Schema(description = "식당 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long restaurantId;

    @Schema(description = "간편결제 비밀번호", example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 6, max = 6, message = "비밀번호는 6자여야 합니다.")
    private String paymentPassword;
}
