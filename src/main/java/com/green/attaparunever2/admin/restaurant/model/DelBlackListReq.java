package com.green.attaparunever2.admin.restaurant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DelBlackListReq {
    @Schema(description = "관리자 PK", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private long adminId;

    @Schema(description = "식당 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long restaurantId;

    @Schema(description = "유저 PK", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private long userId;
}
