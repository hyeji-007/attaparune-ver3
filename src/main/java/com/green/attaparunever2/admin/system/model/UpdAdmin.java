package com.green.attaparunever2.admin.system.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdAdmin {
    @NotEmpty(message = "관리자 PK를 입력해주세요")
    @Schema(description = "관리자 PK", example = "1")
    private Long adminId;

    @NotEmpty(message = "상태값을 입력해주세요")
    @Schema(description = "입점 신청서 상태값(0 : 승인, 1: 거절", example = "0")
    private Integer coalitionState;
}
