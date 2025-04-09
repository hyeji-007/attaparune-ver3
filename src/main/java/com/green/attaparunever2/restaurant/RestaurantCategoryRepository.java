package com.green.attaparunever2.restaurant;


import com.green.attaparunever2.entity.RestaurantCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Long> {
    Optional<RestaurantCategory> findRestaurantCategoryByCategoryId(Long CategoryId);
}
