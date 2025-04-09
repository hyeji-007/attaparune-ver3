package com.green.attaparunever2.user.user_payment_member.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserPaymentMemberDto {
    long orderId;
    long userId;
    LocalDateTime selectDate;
    int point;
    int approvalStatus;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
