package com.green.attaparunever2.reservation;

import com.green.attaparunever2.entity.Order;
import com.green.attaparunever2.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByOrder(Order order);
}
