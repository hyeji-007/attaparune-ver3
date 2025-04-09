package com.green.attaparunever2.order.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class RestaurantOrderDto {
    private Long orderId;
    private Long restaurantId;
    private Long userId;
    private String userName;
    private Long reservationId;
    private String reservationTime;
    private int reservationPeopleCount;
    private String userPhone;
    private List<OrderDetailDto> orderDetails;
}
