package com.green.attaparunever2.admin.company.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(title = "회사포인트 구매 결재 요청전 임시 저장 정보")
public class AdminCompanyPaymentTempPostReq {
    @Schema(description = "결재 orderId(uuid로 생성한 랜덤한 값)", requiredMode = Schema.RequiredMode.REQUIRED)
    private String orderId;
    @Schema(description = "결재 금액", requiredMode = Schema.RequiredMode.REQUIRED)
    @Positive(message = "결재 금액이 올바르게 입력되지 않았습니다.")
    private int amount;
}
