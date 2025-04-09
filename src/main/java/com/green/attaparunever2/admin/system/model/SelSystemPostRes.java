package com.green.attaparunever2.admin.system.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SelSystemPostRes {
    @Schema(description = "게시글 PK")
    private long inquiryId;

    @Schema(description = "접수일자")
    private LocalDateTime createdAt;

    @Schema(description = "관리년도")
    private String year;

    @Schema(description = "사용자 구분")
    private long roleCode;

    @Schema(description = "문의 구분")
    private long postCode;

    @Schema(description = "고객명")
    private String name;

    @Schema(description = "처리 상태")
    private int commentYn;
}
