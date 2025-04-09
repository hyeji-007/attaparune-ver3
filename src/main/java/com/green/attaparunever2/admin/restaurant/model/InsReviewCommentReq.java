package com.green.attaparunever2.admin.restaurant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InsReviewCommentReq {
    @Schema(description = "관리자 PK", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private long adminId;

    @Schema(description = "주문 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long orderId;

    @Schema(description = "댓글 내용", example = "감사합니다.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String commentText;
}

