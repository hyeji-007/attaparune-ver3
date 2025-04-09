package com.green.attaparunever2.company.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DelRefundReq {
    @Schema(description = "환불 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long refundId;

    @Schema(description = "관리자 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long adminId;
}
