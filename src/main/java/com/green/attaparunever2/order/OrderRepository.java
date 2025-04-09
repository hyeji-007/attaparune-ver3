package com.green.attaparunever2.order;

import com.green.attaparunever2.entity.Order;
import com.green.attaparunever2.entity.Restaurant;
import com.green.attaparunever2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderId(Long orderId);

    List<Order> findByRestaurantIdAndUserId(Restaurant restaurant, User user);

    List<Order> findByUserId(User user);

    @Query(value = "SELECT order_detail_id FROM order_detail WHERE menu_id = :menuId LIMIT 1", nativeQuery = true)
    Long findOrderDetailIdByMenuId(@Param("menuId") Long menuId);

}
