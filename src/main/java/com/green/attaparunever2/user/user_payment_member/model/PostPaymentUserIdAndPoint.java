package com.green.attaparunever2.user.user_payment_member.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPaymentUserIdAndPoint {
    private long orderId;
    private long userId;
    private int point;
    private int approvalStatus;

    public PostPaymentUserIdAndPoint(long orderId, long userId, int point, int approvalStatus) {
        this.orderId = orderId;
        this.userId = userId;
        this.point = point;
        this.approvalStatus = approvalStatus;
    }
}
