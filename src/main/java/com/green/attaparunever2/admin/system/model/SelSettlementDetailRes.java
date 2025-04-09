package com.green.attaparunever2.admin.system.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelSettlementDetailRes {
    @Schema(description = "정산 일자")
    private String startDate;
    @Schema(description = "정신 일자")
    private String endDate;
    @Schema(description = "총 금액")
    private int totalPrice;
    @Schema(description = "식당 PK")
    private String restaurantId;
    @Schema(description = "식당 이름")
    private String restaurantName;
    @Schema(description = "입금 미입급 상태 0 : 미입금 1 : 입금")
    private int state;
    @Schema(description = "최종 정산일")
    private String paymentDay;
}
