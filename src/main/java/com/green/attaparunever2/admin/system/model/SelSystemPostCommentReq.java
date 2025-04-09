package com.green.attaparunever2.admin.system.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelSystemPostCommentReq {
    @NotEmpty(message = "문의 PK를 입력해주세요")
    @Schema(description = "문의 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long inquiryId;
}
