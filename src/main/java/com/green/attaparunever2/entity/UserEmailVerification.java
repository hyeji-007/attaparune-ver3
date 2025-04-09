package com.green.attaparunever2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class UserEmailVerification extends CreatedAt{
    @Id
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 100)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiredDate;
}
