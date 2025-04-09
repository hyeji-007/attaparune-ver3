package com.green.attaparunever2.user.user_payment_member.scheduler;

import lombok.Getter;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Getter
public class TicketTask implements Delayed {
    private final long executeTime; // 실행 시간
    private final Long orderId;

    public TicketTask(long orderId, long delay) {
        this.orderId = orderId;
        this.executeTime = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(executeTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.executeTime, ((TicketTask) o).executeTime);
    }

}
