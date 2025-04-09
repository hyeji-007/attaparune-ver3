package com.green.attaparunever2.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRestaurantMainLimit3Req {
    @JsonIgnore
    private int filterType;

    private Long userId;
}
