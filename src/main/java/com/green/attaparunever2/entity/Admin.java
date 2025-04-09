package com.green.attaparunever2.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Admin extends UpdatedAt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long adminId;

    @ManyToOne
    @JoinColumn(name = "code", nullable = false)
    private Code code;

    @Column
    private Long divisionId;

    @Column(length = 50, unique = true)
    private String aid;

    @Column(length = 100)
    private String apw;

    @Column(length = 20)
    private String name;

    @Column
    private Integer coalitionState;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 11)
    private String phone;
}
