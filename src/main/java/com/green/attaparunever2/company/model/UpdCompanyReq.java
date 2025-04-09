package com.green.attaparunever2.company.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdCompanyReq {
    @Schema(description = "회사 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long companyId;

    @Schema(description = "관리자 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long adminId;

    @Schema(description = "회사명", example = "그린아트")
    private String name;

    @Schema(description = "회사 주소", example = "대구 중구")
    private String address;

    @Schema(description = "회사 대표명", example = "엄준식")
    private String ceoName;
}
