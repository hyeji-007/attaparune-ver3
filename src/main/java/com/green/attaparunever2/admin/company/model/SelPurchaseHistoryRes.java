package com.green.attaparunever2.admin.company.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelPurchaseHistoryRes {
    @Schema(description = "구매일자")
    private String createdAt;

    @Schema(description = "구매 포인트")
    private int pointAmount;
}
