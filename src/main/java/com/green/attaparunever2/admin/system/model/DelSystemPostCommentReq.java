package com.green.attaparunever2.admin.system.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DelSystemPostCommentReq {
    @NotEmpty(message = "관리자 PK를 입력해주세요")
    @Schema(description = "관리자 PK", example = "1")
    private Long adminId;

    @NotEmpty
    @Schema(description = "문의답변 Pk", example = "1")
    private Long inquiryCommentId;

}
