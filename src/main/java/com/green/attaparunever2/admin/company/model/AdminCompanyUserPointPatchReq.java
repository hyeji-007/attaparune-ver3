package com.green.attaparunever2.admin.company.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Schema(title = "회사포인트 구매 요청 정보")
@Getter
@Setter
public class AdminCompanyUserPointPatchReq {
    @Schema(description = "입금할 사용자 PK", requiredMode = Schema.RequiredMode.REQUIRED)
    private long userId;
    @Schema(description = "입금할 포인트", requiredMode = Schema.RequiredMode.REQUIRED)
    @Positive
    private int pointAmount;
}
