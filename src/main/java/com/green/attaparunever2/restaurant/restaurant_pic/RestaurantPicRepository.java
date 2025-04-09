package com.green.attaparunever2.restaurant.restaurant_pic;

import com.green.attaparunever2.entity.RestaurantPic;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RestaurantPicRepository extends JpaRepository<RestaurantPic, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM RestaurantPic rp WHERE rp.restaurantId.restaurantId = :restaurantId AND rp.picId IN :picIds")
    int deleteRestaurantPics(@Param("restaurantId") Long restaurantId, @Param("picIds") List<Long> picIds);
}
