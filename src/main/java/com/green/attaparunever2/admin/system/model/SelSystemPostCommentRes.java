package com.green.attaparunever2.admin.system.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelSystemPostCommentRes {
    @Schema(description = "문의답변 PK")
    private Long inquiryCommentId;
    @Schema(description = "시스템 관리자 PK")
    private Long adminId;
    @Schema(description = "문의 PK")
    private Long inquiryId;
    @Schema(description = "문의 내용")
    private String commentDetail;
    @Schema(description = "생성일")
    private String createdAt;
    @Schema(description = "수정일")
    private String updatedAt;
}
