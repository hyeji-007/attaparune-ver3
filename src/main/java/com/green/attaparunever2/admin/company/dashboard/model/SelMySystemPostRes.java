package com.green.attaparunever2.admin.company.dashboard.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelMySystemPostRes {
    @Schema(description = "게시글 PK")
    private Long inquiryId;
    @Schema(description = "게시글 제목")
    private String inquiryTitle;
    @Schema(description = "게시글 내용")
    private String inquiryDetail;
    @Schema(description = "불편문의구분")
    private String postCode;
    @Schema(description = "회사 관리자 PK")
    private Long adminId;
    @Schema(description = "게시글 작성자 PK")
    private String name;
    @Schema(description = "댓글 존재 상태 여부 0 : 없음, 1 : 존재")
    private String commentState;
}
