package com.green.attaparunever2.admin.system.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsSystemPostCommentReq {
    @NotEmpty(message = "문의 PK를 입력해주세요")
    @Schema(description = "문의 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long inquiryId;
    @NotEmpty(message = "시스템 관리자 PK를 입력해주세요")
    @Schema(description = "시스템 관리자 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long adminId;
    @NotEmpty(message = "문의답변을 입력해주세요")
    @Schema(description = "문의 답변", example = "안녕하세요", requiredMode = Schema.RequiredMode.REQUIRED)
    private String commentDetail;
}
