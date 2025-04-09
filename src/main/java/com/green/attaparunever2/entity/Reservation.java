package com.green.attaparunever2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Reservation extends UpdatedAt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long reservationId;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private LocalDateTime reservationTime;

    @Column(nullable = false, columnDefinition = "int default 1")
    private int reservationPeopleCount;

    @Column(length = 100)
    private String reservationCancelReason;

    @Column(length = 11)
    private String userPhone;
}
