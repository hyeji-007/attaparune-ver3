package com.green.attaparunever2.restaurant;

import com.green.attaparunever2.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query(value = "SELECT restaurant_id FROM restaurant ORDER BY restaurant_id DESC LIMIT 1", nativeQuery = true)
    Long findFirstByLatestRestaurantId();

    Optional<Restaurant> findById(Long restaurantId);
}
