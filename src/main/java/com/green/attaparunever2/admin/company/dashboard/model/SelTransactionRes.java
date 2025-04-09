package com.green.attaparunever2.admin.company.dashboard.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelTransactionRes {
    @Schema(description = "현재 포인트")
    private int currentPoint;
    @Schema(description = "지불 포인트")
    private int allowPoint;
    @Schema(description = "구매 포인트")
    private int buyPoint;
}
