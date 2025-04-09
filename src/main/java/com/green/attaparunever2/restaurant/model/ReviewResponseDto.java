package com.green.attaparunever2.restaurant.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReviewResponseDto {
    private List<ReviewDto> reviews;  // 식당 리뷰 리스트
    private List<RatingCountDto> ratingCounts;  // 별점별 리뷰 개수 리스트
    private String restaurantName;
    private double avgRating;
}
