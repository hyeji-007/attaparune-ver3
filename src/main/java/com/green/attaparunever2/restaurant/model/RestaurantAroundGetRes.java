package com.green.attaparunever2.restaurant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantAroundGetRes {
    @Schema(description = "식당 PK")
    private long restaurantId;
    @Schema(description = "식당 이름")
    private String restaurantName;
    @Schema(description = "식당 주소")
    private String restaurantAddress;
    @Schema(description = "식당 평균 식사 시간")
    private double avgRestaurant;
    @Schema(title = "식당 평균 별점")
    private double avgRating;
    @Schema(description = "식당 위도")
    private double lat;
    @Schema(description = "식당 경도")
    private double lng;
    @Schema(description = "식당 사진 리스트")
    private List<RestaurantPicDto> restaurantArroundPicList;
}
