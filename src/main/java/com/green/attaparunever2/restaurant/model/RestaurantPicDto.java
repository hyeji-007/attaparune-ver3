package com.green.attaparunever2.restaurant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantPicDto {
    private long restaurantId;
    private long picId;
    @JsonProperty("filePath")
    private String picName /*picName*/;
}
