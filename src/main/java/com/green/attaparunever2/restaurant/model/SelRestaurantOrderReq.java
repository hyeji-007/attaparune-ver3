package com.green.attaparunever2.restaurant.model;

import com.green.attaparunever2.common.model.Paging;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelRestaurantOrderReq extends Paging {
    private long restaurantId;
    private String startDate;
    private String endDate;


    // 부모 클래스의 필드도 초기화하는 생성자
    public SelRestaurantOrderReq(int page, int size, long restaurantId, String startDate, String endDate) {
        super(page, size); // 부모 클래스(Paging)의 생성자 호출
        this.restaurantId = restaurantId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
