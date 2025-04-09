package com.green.attaparunever2.admin.system.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {
    @Schema(description = "주문PK")
    private Long orderId;
    @Schema(description = "상태값 (0: 거절, 1: 승인)")
    private int status;
}
