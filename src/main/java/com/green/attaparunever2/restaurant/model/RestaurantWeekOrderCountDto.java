package com.green.attaparunever2.restaurant.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RestaurantWeekOrderCountDto {
    private String date;
    private int reservationOrders;
    private int seatedOrders;
}
