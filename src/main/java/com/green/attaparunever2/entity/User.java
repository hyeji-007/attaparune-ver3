package com.green.attaparunever2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends UpdatedAt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "code", nullable = false)
    private Code code;

    @Column(nullable = false, length = 12, unique = true)
    private String uid;

    @Column(nullable = false, length = 100)
    private String upw;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(length = 20)
    private String nickName;

    @Column(length = 100)
    private String userPic;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int point;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int activation;
}
