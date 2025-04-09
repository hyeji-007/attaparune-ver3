package com.green.attaparunever2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class PaymentInfoTmp extends CreatedAt {
    @Id
    @Column
    private String orderId;

    @Column
    private int amount;
}
