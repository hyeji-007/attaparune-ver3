package com.green.attaparunever2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Refund extends UpdatedAt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundId;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Column(nullable = false)
    private int refundPoint;

    @Column(length = 300, nullable = false)
    private String refundDetail;

    @Column(nullable = false)
    private int refundAmount;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int refundYn;

}
