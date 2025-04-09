package com.green.attaparunever2.restaurant.restaurant_menu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCategoryReq {
    @JsonIgnore
    private long categoryId;
    private long restaurantId;
    private String categoryName;
}
