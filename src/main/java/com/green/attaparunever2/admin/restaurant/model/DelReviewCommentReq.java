package com.green.attaparunever2.admin.restaurant.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DelReviewCommentReq {
    @NotEmpty(message = "식당 PK를 입력해주세요")
    @Schema(description = "식당 PK", example = "1")
    private Long restaurantId;

    @NotEmpty(message = "주문 PK를 입력해주세요")
    @Schema(description = "주문 PK", example = "1")
    private Long orderId;
}
