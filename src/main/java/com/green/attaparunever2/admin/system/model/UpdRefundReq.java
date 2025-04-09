package com.green.attaparunever2.admin.system.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdRefundReq {
    @NotEmpty(message = "환불 PK를 입력해주세요")
    @Schema(description = "환불 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long refundId;

    @NotEmpty(message = "상태값을 변경해주세요")
    @Schema(description = "상태값", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer refundYn;
}
