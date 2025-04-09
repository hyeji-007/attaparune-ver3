package com.green.attaparunever2.user.user_payment_member.scheduler;

import lombok.Getter;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Getter
public class UserPaymentMemberTask implements Delayed {
    private final long executeTime; // 실행 시간
    private final Long orderId;
    private final Long userId;

    public UserPaymentMemberTask(long orderId, long userId, long delay) {
        this.orderId = orderId;
        this.userId = userId;
        this.executeTime = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(executeTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.executeTime, ((UserPaymentMemberTask) o).executeTime);
    }

}
