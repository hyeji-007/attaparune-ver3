package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.entity.MealTime;
import com.green.attaparunever2.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MealTimeRepository extends JpaRepository<MealTime, Long> {
    Optional<MealTime> findByOrderId(Order orderId);
}
