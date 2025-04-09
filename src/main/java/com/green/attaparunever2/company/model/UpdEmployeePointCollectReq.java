package com.green.attaparunever2.company.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdEmployeePointCollectReq {
    @Schema(description = "유저 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long userId;

    @Schema(description = "관리자 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long adminId;

    @Schema(description = "회수 포인트", example = "10000", requiredMode = Schema.RequiredMode.REQUIRED)
    private int point;
}
