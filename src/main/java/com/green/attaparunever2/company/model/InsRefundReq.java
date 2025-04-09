package com.green.attaparunever2.company.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsRefundReq {
    @Schema(description = "관리자 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long adminId;

    @Schema(description = "환불 포인트", example = "100000", requiredMode = Schema.RequiredMode.REQUIRED)
    private int refundPoint;

    @Schema(description = "환불 사유", example = "회사 폐업", requiredMode = Schema.RequiredMode.REQUIRED)
    private String refundDetail;
}
