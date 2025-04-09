package com.green.attaparunever2.restaurant.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelRestaurantWeekOrderReq {
    private long restaurantId;
    private String startDate;
    private String endDate;
}
