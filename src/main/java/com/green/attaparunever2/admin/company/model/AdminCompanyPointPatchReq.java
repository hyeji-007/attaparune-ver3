package com.green.attaparunever2.admin.company.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@Schema(title = "회사포인트 구매 요청 정보")
public class AdminCompanyPointPatchReq {
    @Schema(description = "결재 orderId(uuid로 생성한 랜덤한 값)", requiredMode = Schema.RequiredMode.REQUIRED)
    private String orderId;
    @Schema(description = "결재 금액", requiredMode = Schema.RequiredMode.REQUIRED)
    @Positive(message = "결재 금액이 올바르게 입력되지 않았습니다.")
    private int amount;
    @Schema(description = "관리자 PK", requiredMode = Schema.RequiredMode.REQUIRED)
    @Positive(message = "관리자 PK가 올바르지 않습니다.")
    private long adminId;
}
