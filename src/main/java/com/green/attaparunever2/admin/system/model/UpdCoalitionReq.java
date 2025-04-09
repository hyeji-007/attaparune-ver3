package com.green.attaparunever2.admin.system.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdCoalitionReq {
    @Schema(description = "시스템 관리자 PK", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    private long adminSystemId;

    @Schema(description = "식당 관리자 PK", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private long adminCompanyAndRestaurantId;
}
