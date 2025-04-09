package com.green.attaparunever2.admin.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelRefundRes {
    @Schema(description = "환불 금액")
    private int refundAmount;
    @Schema(description = "환불 포인트")
    private int refundPoint;
    @Schema(description = "환불 사유")
    private String refundDetail;
    @Schema(description = "환불 상태")
    private int refundYn;
    @Schema(description = "회사 이름")
    private String name;
    @Schema(description = "환불 일자")
    private String createdAt;
}
