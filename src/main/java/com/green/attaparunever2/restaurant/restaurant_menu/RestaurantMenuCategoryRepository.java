package com.green.attaparunever2.restaurant.restaurant_menu;

import com.green.attaparunever2.entity.Restaurant;
import com.green.attaparunever2.entity.RestaurantMenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantMenuCategoryRepository extends JpaRepository<RestaurantMenuCategory, Long> {
    Optional<RestaurantMenuCategory> findByRestaurantAndCategoryName(Restaurant restaurant, String categoryName);
}
