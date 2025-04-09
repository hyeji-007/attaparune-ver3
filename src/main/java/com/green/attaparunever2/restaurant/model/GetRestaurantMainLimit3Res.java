package com.green.attaparunever2.restaurant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRestaurantMainLimit3Res {
    @Schema(description = "식당 PK", example = "1")
    private long restaurantId;

    @Schema(description = "식당 이름")
    private String restaurantName;

    @Schema(description = "식당 설명")
    private String restaurantDescription;

    @Schema(description = "식당 사진 리스트")
    private RestaurantPicDto restaurantPic;
}
