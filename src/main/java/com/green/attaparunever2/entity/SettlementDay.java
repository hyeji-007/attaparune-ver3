package com.green.attaparunever2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class SettlementDay extends UpdatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    @Column(name = "settlement_day_id")
    private Long settlementDayId;

    @ManyToOne
    @JoinColumn(name = "code", nullable = false)
    private Code code;
}
