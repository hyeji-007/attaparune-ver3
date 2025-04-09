package com.green.attaparunever2.user.user_payment_member.scheduler;

import com.green.attaparunever2.order.OrderMapper;
import com.green.attaparunever2.order.model.OrderAccessPatchReq;
import com.green.attaparunever2.reservation.ReservationMapper;
import com.green.attaparunever2.reservation.model.ReservationDto;
import com.green.attaparunever2.user.user_payment_member.UserPaymentMemberMapper;
import com.green.attaparunever2.user.user_payment_member.model.UserGetPaymentInfoReq;
import com.green.attaparunever2.user.user_payment_member.model.UserPatchPaymentMemberReq;
import com.green.attaparunever2.user.user_payment_member.model.UserPaymentMemberDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

@Service
public class UserPaymentMemberScheduler {
    private final DelayQueue<UserPaymentMemberTask> queue = new DelayQueue<>();
    private final UserPaymentMemberMapper userPaymentMemberMapper;
    private final SimpMessagingTemplate messagingTemplate;

    public UserPaymentMemberScheduler(UserPaymentMemberMapper userPaymentMemberMapper
            , SimpMessagingTemplate messagingTemplate) {
        this.userPaymentMemberMapper = userPaymentMemberMapper;
        this.messagingTemplate = messagingTemplate;
        startProcessing(); // 큐를 처리하는 쓰레드 실행
    }

    // 5분 뒤 자동 취소 작업 추가
    public void scheduleCancellation(Long orderId, Long userId) {
        queue.add(new UserPaymentMemberTask(orderId, userId, TimeUnit.MINUTES.toMillis(5)));
    }

    // 큐에서 작업 꺼내서 실행하는 쓰레드
    private void startProcessing() {
        new Thread(() -> {
            while (true) {
                try {
                    UserPaymentMemberTask task = queue.take(); // 10분 후 실행됨
                    cancelIfNotUpdated(task.getOrderId(), task.getUserId());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    // 5분 후에도 미승인일 시 거절로 변경
    private void cancelIfNotUpdated(Long orderId, Long userId) {
        UserGetPaymentInfoReq req = new UserGetPaymentInfoReq();
        req.setUserId(userId);
        req.setOrderId(orderId);

        UserPaymentMemberDto userPaymentMemberDto = userPaymentMemberMapper.selUserPaymentMemberByOrderIdAndUserId(req);

        if (userPaymentMemberDto != null) {
            if (userPaymentMemberDto.getApprovalStatus() == 0) {
                UserPatchPaymentMemberReq p = new UserPatchPaymentMemberReq();
                p.setOrderId(userPaymentMemberDto.getOrderId());
                p.setUserId(userPaymentMemberDto.getUserId());
                p.setApprovalStatus(2);

                int result = userPaymentMemberMapper.patchPaymentMember(p);

                if (result == 1) {
                    /*
                    // 사용자에게 예약 데이터 전송
                    messagingTemplate.convertAndSend(
                            "/queue/user/"+ p.getUserId() +"/user/userPaymentMember",
                            p
                    );*/
                }
            }
        }
    }
}


