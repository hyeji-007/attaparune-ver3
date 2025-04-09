package com.green.attaparunever2.admin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelCompanyEnrollmentRes {
    @Schema(description = "회사 PK")
    private long companyId;
    @Schema(description = "회사 코드")
    private String companyCd;
    @Schema(description = "회사 이름")
    private String name;
    @Schema(description = "회사 주소")
    private String address;
    @Schema(description = "회사 대표 이름")
    private String ceoName;
    @Schema(description = "회사 사업자 번호")
    private String businessNumber;
    @Schema(description = "회사 보유 포인트")
    private int currentPoint ;
    @Schema(description = "신청일자")
    private String createdAt;
}
