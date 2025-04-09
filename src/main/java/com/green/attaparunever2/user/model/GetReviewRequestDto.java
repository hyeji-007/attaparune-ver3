package com.green.attaparunever2.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GetReviewRequestDto {
    @Schema(title = "주문 PK")
    private Long orderId;
    @Schema(title = "식당명")
    private String restaurantName;
    @Schema(title = "리뷰 내용")
    private String reviewText;
    @Schema(title = "리뷰 삭제 요청 날짜")
    private String statusChangedAt;
    @Schema(title = "리뷰 사진")
    private List<String> reviewPic;
}
