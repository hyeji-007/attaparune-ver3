package com.green.attaparunever2.admin.restaurant;

import com.green.attaparunever2.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Long> {
    @Query("SELECT b FROM BlackList b WHERE b.restaurant.restaurantId = :restaurantId AND b.user.userId = :userId")
    Optional<BlackList> findByRestaurantIdAndUserId(Long restaurantId, Long userId);
}
