package com.green.attaparunever2.admin.company.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelDepositDetailRes {
    @Schema(description = "입금 일자")
    private String createdAt;

    @Schema(description = "입금 금액")
    private int pointAmount;

    @Schema(description = "유저 Pk")
    private Long userId;

    @Schema(description = "유저 이름")
    private String name;
}
