package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.entity.Order;
import com.green.attaparunever2.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByOrder(Order order);

    Optional<Ticket> findByOrderOrderId(Long orderId);

    @Modifying
    @Transactional
    @Query("UPDATE Ticket t SET t.ticketStatus = :status, t.useDate = :useDate WHERE t.ticketId = :ticketId")
    int updateTicketStatusAndUseDate(Long ticketId, int status, LocalDateTime useDate);
}
