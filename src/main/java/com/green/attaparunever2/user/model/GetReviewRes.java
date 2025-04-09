package com.green.attaparunever2.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Schema(title = "리뷰 정보")
public class GetReviewRes {
    @Schema(title = "주문 PK")
    private Long orderId;

    @Schema(title = "식당 이름")
    private String restaurantName;

    @Schema(title = "식당 사진")
    private String picName;

    @Schema(title = "메뉴 이름")
    private List<String> menuName;

    @Schema(title = "리뷰 별점")
    private int rating;

    @Schema(title = "리뷰 내용")
    private String reviewText;

    @Schema(title = "작성일자")
    private String createdAt;

    @Schema(title = "식당 PK")
    private long restaurantId;

    @Schema(title = "댓글 내용")
    private String commentText;

    @Schema(title = "댓글 작성일")
    private String commentCreatedAt;

    @Schema(title = "리뷰 사진")
    private List<String> reviewPic;
}

