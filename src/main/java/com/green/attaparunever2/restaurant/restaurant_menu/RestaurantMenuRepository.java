package com.green.attaparunever2.restaurant.restaurant_menu;

import com.green.attaparunever2.entity.RestaurantMenu;
import com.green.attaparunever2.entity.RestaurantMenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantMenuRepository extends JpaRepository<RestaurantMenu, Long> {
    int countByCategoryId(RestaurantMenuCategory category);
}
