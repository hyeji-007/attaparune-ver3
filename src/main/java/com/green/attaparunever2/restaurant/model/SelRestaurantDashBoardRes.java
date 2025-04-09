package com.green.attaparunever2.restaurant.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SelRestaurantDashBoardRes {
    private Long restaurantId;
    private Integer dayPoint;
    private Integer monthPoint;
    private List<RestaurantWeekOrderCountDto> weekOrderList;
}
