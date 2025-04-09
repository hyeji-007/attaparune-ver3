package com.green.attaparunever2.order.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class OrderDetailDto {
    private long orderDetailId;
    private long orderId;
    private long menuId;
    private String menuName;
    private int menuCount;
    private int price;
    private String createdAt;
    private String updatedAt;
}
