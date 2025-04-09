package com.green.attaparunever2.admin.company.dashboard.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelTransactionReq {
    @NotEmpty(message = "회사 PK를 입력해주세요")
    @Schema(description = "회사 PK", example = "1")
    private Long companyId;

    @NotEmpty(message = "년도와 월을 입력해주세요")
    @Schema(description = "년도/월", example = "2025-02")
    private String date;
}
