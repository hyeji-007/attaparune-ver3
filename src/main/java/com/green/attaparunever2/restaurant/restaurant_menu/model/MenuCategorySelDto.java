package com.green.attaparunever2.restaurant.restaurant_menu.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MenuCategorySelDto {
    private long categoryId;
    private long restaurantId;
    private String categoryName;
    private String createdAt;
    private String updatedAt;
}
