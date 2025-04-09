package com.green.attaparunever2.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class CompanyPointDeposit extends CreatedAt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depositId;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin adminId;

    @Column(nullable = false)
    private String paymentKey;

    @Column(nullable = false)
    private int pointAmount;

    @Column(nullable = false)
    private int cashAmount;
}
