package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.config.security.AuthenticationFacade;
import com.green.attaparunever2.entity.MealTime;
import com.green.attaparunever2.entity.Order;
import com.green.attaparunever2.entity.Restaurant;
import com.green.attaparunever2.entity.Ticket;
import com.green.attaparunever2.order.OrderRepository;
import com.green.attaparunever2.order.ticket.model.*;
import com.green.attaparunever2.restaurant.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketMapper mapper;
    private final TicketMapper ticketMapper;
    private final SimpMessagingTemplate messagingTemplate;
    private final TicketRepository ticketRepository;
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final MealTimeRepository mealTimeRepository;
    private final AuthenticationFacade authenticationFacade;
    //private final OrderMapper orderMapper;
    //private final PaymentUserMapper paymentUserMapper;

//    public long postTicket(TicketPostReq p) {
//
//        OrderDto order = orderMapper.getOrder(new OrderGetReq(p.getOrderId()));
//        if (order == null) {
//            throw new CustomException("해당 주문이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
//        }
//
//        long orderId = order.getOrderId();
//        int totalPrice = order.getTotalPrice();
//
//        // 사용자의 포인트 조회
//        PaymentUserDto userPoint = paymentUserMapper.getPoint(orderId);
//
//        if (userPoint == null) {
//            throw new CustomException("등록된 포인트가 없습니다.", HttpStatus.NOT_FOUND);
//        }
//
//        int point = userPoint.getPoint();
//
//        if (point < totalPrice) {
//            throw new RuntimeException("사용자의 포인트가 부족합니다.");
//        }
//
//        if (point != totalPrice) {
//            throw new RuntimeException("포인트와 주문 금액이 일치하지 않습니다.");
//        }
//
//        return mapper.postTicket(p);
//    }

    public TicketGetRes getTicket(TicketGetReq p) {
        TicketGetRes res = new TicketGetRes();

        TicketDto ticket = mapper.getTicket(p.getTicketId());
        res.setTicket(ticket);

        if (ticket == null) {
            throw new RuntimeException("해당 주문에 대한 식권이 존재하지 않습니다.");
        }

        return res;
    }

    // 식권 사용 완료 처리
    @Transactional
    public int updTicket(TicketUpdReq req) {
        // 식권 사용일 조회
        TicketUseDateSelRes res = mapper.selTicketUseDate(req.getTicketId());

        Ticket ticket = ticketRepository.findById(req.getTicketId())
                .orElseThrow(() -> new CustomException("해당 식권이 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        Order order = orderRepository.findById(ticket.getOrder().getOrderId())
                .orElseThrow(() -> new CustomException("해당 주문이 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(order.getRestaurantId().getRestaurantId())
                .orElseThrow(() -> new CustomException("해당 식당이 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        // 식권이 존재하지 않으면 예외 처리
        if (res == null) {
            throw new CustomException("존재하지 않는 티켓입니다.", HttpStatus.BAD_REQUEST);
        }

        // 이미 사용된 식권일 경우 예외 처리
        if (ticket.getTicketStatus() == 1) {
            throw new CustomException("이미 사용된 티켓입니다.", HttpStatus.BAD_REQUEST);
        }

        // 간편 결제 비밀번호 인증
        if (!BCrypt.checkpw(req.getPaymentPassword(), restaurant.getPaymentPassword())) {
            throw new CustomException("간편 결제 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 해당 식당에서 발행된 식권이 맞는지 검증
        if (!ticket.getOrder().getRestaurantId().getRestaurantId().equals(req.getRestaurantId())) {
            throw new CustomException("해당 식당에 대한 식권이 아닙니다.", HttpStatus.BAD_REQUEST);
        }

        // 식권 수정
        ticket.setTicketStatus(1);
        ticket.setUseDate(LocalDateTime.now());
        ticketRepository.save(ticket);

        int result = ticketRepository.updateTicketStatusAndUseDate(ticket.getTicketId(), 1, LocalDateTime.now());

        // 식권 수정에 실패
        if (result == 0) {
            throw new CustomException("식권 사용에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }

        // 식권 사용 시 식사 종료 시간 데이터 삽입
        if (ticket.getTicketStatus() == 1) {
            MealTime mealTime = mealTimeRepository.findByOrderId(ticket.getOrder())
                    .orElseThrow(() -> new CustomException("해당 주문에 대한 식사 시간이 존재하지 않습니다.", HttpStatus.NOT_FOUND));

            mealTime.setEndMealDate(ticket.getUseDate());
            mealTimeRepository.save(mealTime);
        }

        SelTicketDto dto = ticketMapper.selTicketByTicketId(ticket.getTicketId());

        // 사용자에게 식권사용 완료 메세지 전송
        messagingTemplate.convertAndSend(
                "/queue/reservation/" + dto.getOrderId() + "/user/reservation",
                dto
        );

        return result;
    }

    public long getTicketOne(long userId){
        long result = ticketMapper.selTicketOne(userId);

        return result;
    }
}
