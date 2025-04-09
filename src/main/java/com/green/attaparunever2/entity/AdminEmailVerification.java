package com.green.attaparunever2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AdminEmailVerification extends CreatedAt{
    @Id
    @OneToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "admin_id", nullable = false)
    private Admin admin;

    @Column(nullable = false, length =  100)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiredDate;
}
