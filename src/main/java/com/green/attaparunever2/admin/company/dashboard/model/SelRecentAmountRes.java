package com.green.attaparunever2.admin.company.dashboard.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelRecentAmountRes {
    @Schema(description = "구분명")
    private String note;
    @Schema(description = "PK 값")
    private Long pk;
    @Schema(description = "관리자 PK")
    private Long adminId;
    @Schema(description = "유저 PK")
    private Long userId;
    @Schema(description = "구분")
    private String code;
    @Schema(description = "구분명")
    private String codeName;
    @Schema(description = "포인트")
    private int pointAmount;
    @Schema(description = "현금")
    private int cashAmount;
    @Schema(description = "회사 PK")
    private Long companyId;
    @Schema(description = "사용일자")
    private String createdAt;
    @Schema(description = "사용자 이름")
    private String name;
}
