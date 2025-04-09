package com.green.attaparunever2.restaurant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RestaurantDetailGetReq {
    @Schema(description = "식당 PK", example = "1")
    @Positive(message = "올바르지 않은 식당 PK 값입니다.")
    private long restaurantId;
}
