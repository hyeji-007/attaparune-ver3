package com.green.attaparunever2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class UserPaymentMember extends UpdatedAt{
    @EmbeddedId
    private UserPaymentMemberIds userPaymentMemberIds;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private LocalDateTime selectDate;

    @Column
    private Integer point;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int approvalStatus;
}
