package com.green.attaparunever2.user.user_payment_member.scheduler;

import com.green.attaparunever2.order.OrderMapper;
import com.green.attaparunever2.order.model.OrderSelDto;
import com.green.attaparunever2.order.ticket.TicketMapper;
import com.green.attaparunever2.order.ticket.model.TicketSelDto;
import com.green.attaparunever2.user.user_payment_member.UserPaymentMemberMapper;
import com.green.attaparunever2.user.user_payment_member.model.UserGetPaymentInfoReq;
import com.green.attaparunever2.user.user_payment_member.model.UserPatchPaymentMemberReq;
import com.green.attaparunever2.user.user_payment_member.model.UserPaymentMemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TicketScheduler {
    private final DelayQueue<TicketTask> queue = new DelayQueue<>();
    private final TicketMapper ticketMapper;
    private final OrderMapper orderMapper;

    public TicketScheduler(TicketMapper ticketMapper, OrderMapper orderMapper) {
        this.ticketMapper = ticketMapper;
        this.orderMapper = orderMapper;
        startProcessing(); // 큐를 처리하는 쓰레드 실행
    }

    // 2시간 뒤 자동결재 하는 작업
    public void scheduleCancellation(Long orderId, LocalDateTime time) {
        LocalDateTime now = LocalDateTime.now();

        // 두 시간의 차이 계산 (분 단위)
        long minutesDiff = Duration.between(time, now).toMinutes();
        log.info("minutesDiff: {}", minutesDiff);
        queue.add(new TicketTask(orderId, TimeUnit.MINUTES.toMillis(120 + minutesDiff)));
    }

    // 큐에서 작업 꺼내서 실행하는 쓰레드
    private void startProcessing() {
        new Thread(() -> {
            while (true) {
                try {
                    TicketTask task = queue.take(); // 10분 후 실행됨
                    autoPayment(task.getOrderId());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    // 2시간 후에 자동으로 결재 처리 되게끔
    private void autoPayment(Long orderId) {
        log.info("autoPayment: {}", orderId);
        OrderSelDto dto = orderMapper.selOrderByOrderId(orderId);
        log.info("dto: {}", dto);
        // 2시간뒤에 조회 했을때 예약 상태가 승인 이라면
        if(dto != null && ((dto.getReservationYn() == 1 && dto.getReservationStatus() == 1) || (dto.getReservationYn() == 0 && dto.getReservationStatus() != 3))) {
            TicketSelDto ticketDto = ticketMapper.selTicketByOrderId(orderId);

            if(ticketDto != null && ticketDto.getTicketStatus() != 1) {
                ticketMapper.updTicket(ticketDto.getTicketId());
            }
        }
    }
}
