package com.green.attaparunever2.company.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdEmployeeReq {
    @Schema(description = "관리자 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long adminId;

    @Schema(description = "사용자 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long userId;

    @Schema(description = "사용자 상태(0: 활성화, 1: 비활성화", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private int activation;
}
