package com.green.attaparunever2.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GetReviewDto {
    private Long orderId;
    private String restaurantName;
    private int rating;
    private String reviewText;
    private String createdAt;
    private long restaurantId;
    private String commentText;
    private String commentCreatedAt;
}
