package com.green.attaparunever2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
public class CompanyTicketCreationTime extends UpdatedAt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long creationId;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false)
    private LocalTime creationTimeStart;

    @Column(nullable = false)
    private LocalTime creationTimeEnd;

    @Column(nullable = false, length = 30)
    private String creationTimeName;
}
