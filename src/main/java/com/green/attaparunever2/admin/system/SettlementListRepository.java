package com.green.attaparunever2.admin.system;

import com.green.attaparunever2.entity.SettlementList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementListRepository extends JpaRepository<SettlementList, Long> {

    @Modifying
    @Query(value = """
        INSERT INTO settlement_list (ticket_id)
        SELECT A.ticket_id
        FROM ticket A
        INNER JOIN `order` B
        ON A.order_id = B.order_id
        WHERE B.restaurant_id = :restaurantId
            AND ticket_id NOT IN(SELECT ticket_id FROM settlement_list B) 
            """
        , nativeQuery = true)
    void insertSettlementList(@Param("restaurantId") Long restaurantId);
}
