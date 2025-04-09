package com.green.attaparunever2.admin.system.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdSystemPostCommentReq {
    @NotEmpty(message = "문의답변 PK를 입력해주세요")
    @Schema(description = "문의 답변 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long inquiryCommentId;

    @NotEmpty(message = "문의 내용을 입력해주세요")
    @Schema(description = "문의 내용", example = "반갑습니다", requiredMode = Schema.RequiredMode.REQUIRED)
    private String commentDetail;
}
