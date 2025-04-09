package com.green.attaparunever2.admin.company.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelCompanyAndAdminInfoReq {
    @Schema(description = "관리자 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long adminId;
}
