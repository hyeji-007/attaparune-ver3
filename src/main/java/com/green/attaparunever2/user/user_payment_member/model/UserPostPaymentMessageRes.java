package com.green.attaparunever2.user.user_payment_member.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPostPaymentMessageRes {
    private long userId;
    private long orderUserId;
    private String orderUserName;
    private long orderId;
    private int point;
    private String typeMessage;
}
