package com.green.attaparunever2.admin.system.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsSettlementListReq {

    @NotEmpty(message = "식당 PK를 입력해주세요")
    @Schema(description = "식당 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long restaurantId;
}
