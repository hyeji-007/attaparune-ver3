package com.green.attaparunever2.restaurant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRestaurantMainRes {
    @Schema(description = "식당 PK")
    private long restaurantId;
    @Schema(description = "식당 이름")
    private String restaurantName;
    @Schema(description = "식당 주소")
    private String restaurantAddress;
    @Schema(description = "평균 식사 시간")
    private int avgRestaurant;
    @Schema(description = "평균 별점")
    private double avgRating;
    @Schema(description = "식당 사진 리스트")
    private RestaurantPicDto restaurantAroundPicList;
}
