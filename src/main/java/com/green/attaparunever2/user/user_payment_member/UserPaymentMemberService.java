package com.green.attaparunever2.user.user_payment_member;

import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.config.security.AuthenticationFacade;
import com.green.attaparunever2.entity.*;
import com.green.attaparunever2.order.OrderMapper;
import com.green.attaparunever2.order.OrderRepository;
import com.green.attaparunever2.order.model.OrderSelDto;
import com.green.attaparunever2.order.ticket.TicketMapper;
import com.green.attaparunever2.order.ticket.TicketRepository;
import com.green.attaparunever2.order.ticket.model.TicketSelDto;
import com.green.attaparunever2.reservation.ReservationMapper;
import com.green.attaparunever2.reservation.ReservationRepository;
import com.green.attaparunever2.reservation.model.ReservationDto;
import com.green.attaparunever2.user.UserMapper;
import com.green.attaparunever2.user.UserRepository;
import com.green.attaparunever2.user.model.TicketMakeMessageRes;
import com.green.attaparunever2.user.model.UserDTO;
import com.green.attaparunever2.user.model.UserGetReq;
import com.green.attaparunever2.user.model.UserGetRes;
import com.green.attaparunever2.user.user_payment_member.model.*;
import com.green.attaparunever2.user.user_payment_member.scheduler.TicketScheduler;
import com.green.attaparunever2.user.user_payment_member.scheduler.UserPaymentMemberScheduler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserPaymentMemberService {
    private final UserPaymentMemberMapper userPaymentMemberMapper;
    private final SimpMessagingTemplate messagingTemplate;
    private final UserPaymentMemberScheduler userPaymentMemberScheduler;
    private final OrderMapper orderMapper;
    private final UserMapper userMapper;
    private final TicketMapper ticketMapper;
    private final ReservationMapper reservationMapper;
    private final TicketScheduler ticketScheduler;
    private final AuthenticationFacade authenticationFacade;
    private final UserPaymentMemberRepository userPaymentMemberRepository;
    private final OrderRepository orderRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    //사용자 포인트 조회
    public UserGetPointRes getPoint(long userId) {
        UserGetPointRes point = null;

        try {
            point = userPaymentMemberMapper.getPoint(userId);
        }catch (Exception e) {
            throw new CustomException("포인트 조회시 오류가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }

        return point;
    }

    //결제 등록(미사용 하는 메소드!!!!!!!!!!1)
    @Transactional
    public int postPayment(UserPostPaymentReq p) {
        int result = 0;
        try {
            log.debug("orderId::" + p.getOrderId());
            /*
            함께 결제가 아니라 1명이 계산해야하는 경우
                1.결제해야하는 사람이 가지고있는 포인트를 확인
                2.주문금액보다 포인트가 많은지 확인
                    결제금액은 여러명일시 총 금액/인원수로 한 사람이 결제해야하는 금액을 가져온다

            직관적으로 개발하는편이 우선은 좋을 것 같음
            */

//          1.결제해야하는 사람이 가지고있는 포인트를 확인
            UserGetPointRes userPointInfo = userPaymentMemberMapper.getPoint(p.getUserId());

//          2.주문금액보다 포인트가 많은지 확인
            int price = userPaymentMemberMapper.getOrderPrice(p.getOrderId());

//          1번과 2번을 비교하여 결제할 수 있는지 체크
            if(userPointInfo.getPoint() >= price)
            {
                p.setPoint(price);
                result = userPaymentMemberMapper.insertPaymentMember(p);

                // 사용자에게 결재요청 메세지 보냄
                OrderSelDto orderSelDto = orderMapper.selOrderByOrderId(p.getOrderId());
                List<UserPaymentMemberDto> userPaymentMemberDtoList = userPaymentMemberMapper.selUserPaymentMemberByOrderId(p.getOrderId());
                UserGetReq userGetReq = new UserGetReq();
                userGetReq.setUserId(p.getUserId());

                UserGetRes user = userMapper.selUserByUserId(p.getUserId());
                UserPostPaymentMessageRes messageRes = new UserPostPaymentMessageRes();

                messageRes.setTypeMessage("결재 요청");
                messageRes.setPoint(p.getPoint());
                messageRes.setOrderUserName(user.getName());
                messageRes.setOrderUserId(orderSelDto.getUserId());
                messageRes.setOrderId(p.getOrderId());
                messageRes.setUserId(p.getUserId());

                if(orderSelDto.getUserId() != p.getUserId()) {
                    // 유저 구독 경로로 예약 알림 메시지 전송
                    messagingTemplate.convertAndSend(
                            "/queue/user/" + p.getUserId() + "/user/userPaymentMember",
                            messageRes
                    );

                    // 요청 5분 뒤 업데이트 안할 시 거절 처리할 스케줄러 실행
                    //userPaymentMemberScheduler.scheduleCancellation(user.getOrderId(), user.getUserId());
                }

            } else {
                int amount = price - userPointInfo.getPoint();
                String msg = "결제금액이("+amount+"원) 부족합니다.";
                throw new CustomException(msg, HttpStatus.OK);
            }

        } catch (Exception e) {
            String msg = "결제정보 등록시 에러가 발생하였습니다.";
            throw new CustomException(msg, HttpStatus.BAD_REQUEST);
        }

        return result;
    }

    //유저 결제 멤버 확인
    public List<SelUserPaymentMemberRes> getUserPaymentMember(long orderId) {
        List<SelUserPaymentMemberRes> resList = userPaymentMemberMapper.selUserPaymentMember(orderId);
        return resList;
    }

    //승인 상태 확인
    public List<SelUserOrderApprovalRes> getUserOrderApprovalAccess(long orderId){
        List<SelUserOrderApprovalRes> userOrderApprovalList = userPaymentMemberMapper.selUserOrderApprovalAccess(orderId);
        return userOrderApprovalList;
    }

    //결제요청(티켓 생성)
    @Transactional
    public long postTicket(PostTicketReq p){
        // 1.오더 아이디에 포함된 사용자들이 전부 승인을 했는지 여부
        List<SelUserOrderApprovalRes> list = userPaymentMemberMapper.selUserOrderApprovalAccess(p.getOrderId());

        int sumMenuPrice = userPaymentMemberMapper.sumMenuPrice(p.getOrderId());
        int sumUserPoint = 0;
        for(SelUserOrderApprovalRes item : list) {
            if (item.getApprovalStatus() != 1) {
                throw new CustomException("모두 승인이 되어있지않습니다.", HttpStatus.BAD_REQUEST);
            }
            // 2. 포인트가 사용자에게 실제로 있는지
            if(item.getPoint() > item.getUserPoint()) {
                throw new CustomException("승인할 금액이 부족합니다.", HttpStatus.BAD_REQUEST);
            }
            sumUserPoint += item.getPoint();
        }

        log.info("sumMenuPrice : {}, sumUserPoint : {}", sumMenuPrice, sumUserPoint);
        if(sumMenuPrice != sumUserPoint) {
            log.info("sumUserPoint: {}", sumUserPoint);
            throw new CustomException("금액이 정확하지 않습니다.", HttpStatus.BAD_REQUEST);
        }


        for (SelUserOrderApprovalRes item : list) {
            log.info("userPoint : {}, itemPoint {}", item.getUserPoint(), item.getPoint());

            User user = userRepository.findById(item.getUserId()).orElseThrow(() -> new CustomException("사용자 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST));

            user.setPoint(user.getPoint() - item.getPoint());

            userRepository.save(user);

            //userPaymentMemberMapper.updUserPoint(item.getPoint(), item.getUserId());
        }

        // JPA로 변경
        Ticket ticket = new Ticket();
        Order order = orderRepository.findByOrderId(p.getOrderId()).orElseThrow(() -> new CustomException("주문정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST));

        ticket.setOrder(order);

        // 만료시간 구하기
        Reservation reservation = reservationRepository.findByOrder(order).orElse(null);
        LocalDateTime expiredDate = LocalDateTime.now();

        // 예약이 존재하는 경우 예약시간 + 2시간 후
        if(reservation != null) {
            expiredDate = reservation.getReservationTime();
        }

        expiredDate = expiredDate.plusHours(2);

        ticket.setExpiredDate(expiredDate);

        ticketRepository.save(ticket);

        if(ticket.getTicketId() != null) {
            OrderSelDto orderSelDto = orderMapper.selOrderByOrderId(p.getOrderId());

            // 사장님 구독 경로로 식권생성 완료 메세지 전송
            TicketMakeMessageRes ticketMakeMessageRes = new TicketMakeMessageRes();

            ticketMakeMessageRes.setTypeMessage("주문에 대한 식권 생성됨(즉 예약 완료됨)");
            ticketMakeMessageRes.setOrderId(orderSelDto.getOrderId());
            ticketMakeMessageRes.setUserId(orderSelDto.getUserId());
            ticketMakeMessageRes.setCreatedAt(orderSelDto.getCreatedAt());
            ticketMakeMessageRes.setRestaurantId(orderSelDto.getRestaurantId());
            ticketMakeMessageRes.setReservationYn(orderSelDto.getReservationYn());
            ticketMakeMessageRes.setReservationStatus(orderSelDto.getReservationStatus());

            messagingTemplate.convertAndSend(
                    "/queue/restaurant/" + orderSelDto.getRestaurantId() + "/owner/reservation",
                    ticketMakeMessageRes
            );

            TicketSelDto ticketDto =  ticketMapper.selTicketByOrderId(p.getOrderId());
            ReservationDto reservationDto = reservationMapper.selReservationByOrderId(p.getOrderId());
            LocalDateTime time = ticketDto.getCreatedAt();

            // 예약이 존재하는 경우 예약시간으로
            if(reservationDto != null) {
                time = reservationDto.getReservationTime();
            }

            // 식권생성 후 2 시간 후 결재 처리
            ticketScheduler.scheduleCancellation(p.getOrderId(), time);
        } else {
            throw new CustomException("식권 생성에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }

        return ticket.getTicketId();
    }


//    //결제 인원 조회
//    public int getPaymentMember(long orderId) {
//        int targetCnt = 0;
//        try {
//            targetCnt = userPaymentMemberMapper.getPaymentMember(orderId);
//
//        } catch (Exception e) {
//            String msg = "결제 인원 조회시 에러가 발생하였습니다.";
//            throw new CustomException(msg, HttpStatus.BAD_REQUEST);
//        }
//
//        return targetCnt;
//    }

    //결제 인원 조회 >> 수정
    public List<PaymentMemberDto> getPaymentMemberByName(UserPaymentMemberGetReq p, String name) {
        int size = p.getSize();
        int startIdx = (p.getPage() - 1) * size;
        List<PaymentMemberDto> memberList = userPaymentMemberMapper.getPaymentMemberByName(p.getCompanyId(), name, size, startIdx);
        return memberList;
    }

    @Transactional
    public int updPaymentAmount(UserPaymentAmountPatchReq p) {
        UserPaymentMemberIds ids = new UserPaymentMemberIds(p.getOrderId(), p.getUserId());
        UserPaymentMember userPaymentMember = userPaymentMemberRepository.findById(ids)
                .orElseThrow(() -> new CustomException("해당 결제가 존재하지 않습니다", HttpStatus.BAD_REQUEST));
        userPaymentMember.setPoint(p.getPoint());
        userPaymentMemberRepository.save(userPaymentMember);
        userPaymentMemberRepository.flush();
        if(userPaymentMember.getUserPaymentMemberIds().getUserId() != 0 && userPaymentMember.getUserPaymentMemberIds().getOrderId() != 0) {
            OrderSelDto orderSelDto = orderMapper.selOrderByOrderId(p.getOrderId());

            UserGetRes user = userMapper.selUserByUserId(orderSelDto.getUserId());
            UserPostPaymentMessageRes messageRes = new UserPostPaymentMessageRes();

            messageRes.setTypeMessage("결재 요청");
            messageRes.setPoint(p.getPoint());
            messageRes.setOrderUserName(user.getName());
            messageRes.setOrderUserId(orderSelDto.getUserId());
            messageRes.setOrderId(p.getOrderId());
            messageRes.setUserId(p.getUserId());

            if(orderSelDto.getUserId() != p.getUserId()) {
                // 유저 구독 경로로 예약 알림 메시지 전송
                messagingTemplate.convertAndSend(
                        "/queue/user/" + p.getUserId() + "/user/userPaymentMember",
                        messageRes
                );

                // 요청 5분 뒤 업데이트 안할 시 거절 처리할 스케줄러 실행
                //userPaymentMemberScheduler.scheduleCancellation(user.getOrderId(), user.getUserId());
            }
        }

        return 1;
    }

    public int deletePaymentMember(UserPaymentMemberDelReq p) {
        Long signedUserId = authenticationFacade.getSignedUserId();

        Order order = orderRepository.findByOrderId(p.getOrderId())
                .orElseThrow(() -> new CustomException("해당 주문이 없습니다.", HttpStatus.NOT_FOUND));

        // 해당 주문의 주문자 사용자가 맞는지
        if(order.getUserId().getUserId() != signedUserId) {
            throw new CustomException("해당 주문의 주문자가 아니면 인원삭제가 불가 합니다.", HttpStatus.BAD_REQUEST);
        }

        // 이미 식권이 존재하는 경우 삭제 불가하게
        TicketSelDto ticketDto =  ticketMapper.selTicketByOrderId(p.getOrderId());

        if(ticketDto != null) {
            throw new CustomException("이미 식권이 생성되어 결재인원을 삭제 할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        int result = userPaymentMemberRepository.deleteByOrderIdAndUserId(p.getOrderId());

        return result;
    }



    //내게 온 결제 승인요청 정보조회
    public UserGetPaymentInfoRes getPaymentInfo(UserGetPaymentInfoReq p) {
        p.setOrderUserId(authenticationFacade.getSignedUserId());
        UserGetPaymentInfoRes result = null;
        try {
            /*
             내게 온 결제 정보는 user_payment_member 테이블에 order_id, user_id로 조회 할 수 있지만,
             사용자 화면에 승인요청 정보를 표출시 메뉴명을 보여주고싶다면
              + 식당명, 요청자, 결제id (PK)
             */
            result = userPaymentMemberMapper.getPaymentInfo(p);
        } catch (Exception e) {
            String msg = "내게 온 결제 승인요청 정보조회시 에러가 발생하였습니다.";
            throw new CustomException(msg, HttpStatus.BAD_REQUEST);
        }

        return result;
    }

    //나에게 온 결제 요청 정보 승인 및 거부
    public int patchPaymentMember(UserPatchPaymentMemberReq p) {
        UserPaymentMemberIds ids = new UserPaymentMemberIds(p.getOrderId(), p.getUserId());
        ids.setUserId(p.getUserId());
        ids.setOrderId(p.getOrderId());

        UserPaymentMember userPaymentMember = userPaymentMemberRepository.findById(ids)
                .orElseThrow(() -> new CustomException("해당 결제 승인 요청이 없습니다.", HttpStatus.NOT_FOUND));

        // 이미 식권이 존재하는 경우 수정 불가하게
        TicketSelDto ticketDto =  ticketMapper.selTicketByOrderId(p.getOrderId());

        if(ticketDto != null) {
            throw new CustomException("이미 식권이 생성되어 취소할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        userPaymentMember.setApprovalStatus(p.getApprovalStatus());
        userPaymentMember.setSelectDate(LocalDateTime.now());
        userPaymentMemberRepository.save(userPaymentMember);

        return 1;
    }

    @Transactional
    public int postPaymentMember(UserPostPaymentMemberReq req) {
        OrderSelDto orderSelDto = orderMapper.selOrderByOrderId(req.getOrderId());

        List<PaymentMember> paymentMembers = req.getData();

        // userId와 point 값 체크: 모든 PaymentMember 객체에서 point가 존재하는지 확인
        for (PaymentMember paymentMember : paymentMembers) {
            if (paymentMember.getPoint() == null || paymentMember.getUserId() == null) {
                throw new CustomException("userId와 point의 수가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
            }
        }

        List<UserGetPointRes> userPoints = new ArrayList<>();

        // 각 userId의 포인트 조회
        for (PaymentMember paymentMember : paymentMembers) {
            userPoints.add(userPaymentMemberMapper.getPoint(paymentMember.getUserId()));
        }

        // orderId와 userId가 같은 튜플이 이미 존재하는지 확인
        for (PaymentMember paymentMember : paymentMembers) {
            int existingCount = userPaymentMemberMapper.selUserPaymentMemberCount(req.getOrderId(), paymentMember.getUserId());

            if (existingCount > 0) {
                // 이미 같은 값의 userId 튜플이 존재하는 경우 예외 처리
                throw new CustomException("이미 승인 요청을 보냈습니다.", HttpStatus.BAD_REQUEST);
            }
        }

        // 포인트 초과 시 예외 처리
        for (int i = 0; i < paymentMembers.size(); i++) {
            PaymentMember paymentMember = paymentMembers.get(i);
            int requestedPoint = paymentMember.getPoint();
            int userTablePoint = userPoints.get(i).getPoint();

            if (requestedPoint > userTablePoint) {
                throw new CustomException("현재 보유 금액보다 결제 금액이 큽니다.", HttpStatus.BAD_REQUEST);
            }
        }

        long result = 0;
        // 결제 승인 요청을 위한 데이터 처리
        for (PaymentMember paymentMember : paymentMembers) {
            int status = orderSelDto.getUserId() == paymentMember.getUserId() ? 1 : 0;

            UserPaymentMemberIds ids = new UserPaymentMemberIds(req.getOrderId(), paymentMember.getUserId());
            Order order = new Order();
            order.setOrderId(req.getOrderId());

            User user = new User();
            user.setUserId(paymentMember.getUserId());

            UserPaymentMember userPaymentMember = new UserPaymentMember();
            userPaymentMember.setUserPaymentMemberIds(ids);
            userPaymentMember.setUser(user);
            userPaymentMember.setOrder(order);
            userPaymentMember.setPoint(paymentMember.getPoint());
            userPaymentMember.setApprovalStatus(status);

            userPaymentMemberRepository.save(userPaymentMember);
            userPaymentMemberRepository.flush();

            result = ids.getUserId();
        }


        // 저장에 성공하면 사용자에게 승인 요청을 보냄
        if(result >= 1) {

            List<UserPaymentMemberDto> userPaymentMemberDtoList = userPaymentMemberMapper.selUserPaymentMemberByOrderId(req.getOrderId());

            for(UserPaymentMemberDto user : userPaymentMemberDtoList) {
                if(orderSelDto.getUserId() != user.getUserId()) {
                    // 유저 구독 경로로 예약 알림 메시지 전송
                    UserGetRes selUser = userMapper.selUserByUserId(orderSelDto.getUserId());
                    UserPostPaymentMessageRes messageRes = new UserPostPaymentMessageRes();

                    messageRes.setTypeMessage("결재 요청");
                    messageRes.setPoint(user.getPoint());
                    messageRes.setOrderUserName(selUser.getName());
                    messageRes.setOrderUserId(orderSelDto.getUserId());
                    messageRes.setOrderId(req.getOrderId());
                    messageRes.setUserId(user.getUserId());

                    messagingTemplate.convertAndSend(
                            "/queue/user/" + user.getUserId() + "/user/userPaymentMember",
                            req
                    );

                    // 요청 5분 뒤 업데이트 안할 시 거절 처리할 스케줄러 실행
                    //userPaymentMemberScheduler.scheduleCancellation(user.getOrderId(), user.getUserId());
                }
            }
        }
        return 1;
    }
}
