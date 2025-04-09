package com.green.attaparunever2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Company extends UpdatedAt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long companyId;

    @Column(nullable = false, length = 4, unique = true)
    private String companyCd;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 300)
    private String address;

    @Column(nullable = false, length = 30)
    private String ceoName;

    @Column(nullable = false, length = 10, unique = true)
    private String businessNumber;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int currentPoint;
}
