package com.green.attaparunever2.admin.company.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelCompanyAndAdminInfoRes {
    @Schema(description = "관리자 PK")
    private long adminId;

    @Schema(description = "회사 PK")
    private long companyId;

    @Schema(description = "관리자 ID")
    private String aid;

    @Schema(description = "관리자 이메일")
    private String email;

    @Schema(description = "관리자 연락처")
    private String phone;

    @Schema(description = "제휴 상태")
    private int coalitionState;

    @Schema(description = "회사명")
    private String companyName;

    @Schema(description = "회사 주소")
    private String companyAddress;

    @Schema(description = "회사 대표자명")
    private String companyCeoName;
}
