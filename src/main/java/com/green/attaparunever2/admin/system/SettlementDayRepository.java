package com.green.attaparunever2.admin.system;

import com.green.attaparunever2.entity.SettlementDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SettlementDayRepository extends JpaRepository<SettlementDay, Long> {

    @Query(value = "SELECT code FROM settlement_day", nativeQuery = true)
    String findSettlementDay();

}
