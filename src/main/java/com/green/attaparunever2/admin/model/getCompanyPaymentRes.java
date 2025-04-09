package com.green.attaparunever2.admin.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class getCompanyPaymentRes {
    @Schema(description = "구입 일자")
    private String createdAt;

    @Schema(description = "현금 구입 금액")
    private int cashAmount;

    @Schema(description = "포인트 지급 금액")
    private int pointAmount;

    @Schema(description = "관리자 PK")
    private long adminId;

    @Schema(description = "회사 이름")
    private String name;
}
