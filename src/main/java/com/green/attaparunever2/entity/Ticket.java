package com.green.attaparunever2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.One;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Ticket extends CreatedAt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long ticketId;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private LocalDateTime expiredDate;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int ticketStatus;

    @Column
    private LocalDateTime useDate;
}
