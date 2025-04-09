package com.green.attaparunever2.reservation;

import com.green.attaparunever2.admin.AdminRepository;
import com.green.attaparunever2.admin.restaurant.BlackListRepository;
import com.green.attaparunever2.common.delayed.DelayedTaskScheduler;
import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.entity.*;
import com.green.attaparunever2.order.OrderDetailRepository;
import com.green.attaparunever2.order.OrderMapper;
import com.green.attaparunever2.order.OrderRepository;
import com.green.attaparunever2.order.OrderService;
import com.green.attaparunever2.order.model.OrderAccessPatchReq;
import com.green.attaparunever2.order.model.OrderDetailPostReq;
import com.green.attaparunever2.order.model.OrderPostReq;
import com.green.attaparunever2.order.ticket.MealTimeRepository;
import com.green.attaparunever2.order.ticket.TicketMapper;
import com.green.attaparunever2.order.ticket.TicketRepository;
import com.green.attaparunever2.order.ticket.model.TicketSelDto;
import com.green.attaparunever2.reservation.model.*;
import com.green.attaparunever2.reservation.scheduler.ReservationScheduler;
import com.green.attaparunever2.restaurant.RestaurantRepository;
import com.green.attaparunever2.restaurant.restaurant_menu.RestaurantMenuCategoryRepository;
import com.green.attaparunever2.restaurant.restaurant_menu.RestaurantMenuRepository;
import com.green.attaparunever2.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationMapper reservationMapper;
    private final ReservationScheduler reservationScheduler;
    private final SimpMessagingTemplate messagingTemplate;
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final TicketMapper ticketMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ReservationRepository reservationRepository;
    private final MealTimeRepository mealTimeRepository;
    private final BlackListRepository blackListRepository;
    private final AdminRepository adminRepository;
    private final TicketRepository ticketRepository;
    private final RestaurantMenuRepository restaurantMenuRepository;
    private final DelayedTaskScheduler scheduler;
    private final RestaurantMenuCategoryRepository restaurantMenuCategoryRepository;


    @Transactional
    public int postReservation(ReservationPostReq req) {
        Long createdOrderId = null; // 생성된 주문 PK(프론트로 반환해 줄 값)

        // 현재 예약이 있는지 검사
        /*ReservationDto reservationDto = reservationMapper.selActiveReservationByUserId(req.getUserId());

        if(reservationDto != null) {
            if(reservationDto.getReservationStatus() == 1) {
                TicketSelDto ticketSelDto = ticketMapper.selTicketByOrderId(reservationDto.getOrderId());

                // 식권이 사용되지 않았다면 아직 예약이 미완료 되었다고 판단.
                if(ticketSelDto == null || ticketSelDto.getTicketStatus() != 1) {
                    throw new CustomException(
                            reservationDto.getReservationTime() + "에 예약한 "
                                    + reservationDto.getRestaurantName() + "에 대한 예약이 존재합니다. "
                                    + "예약 요청에 실패했습니다."
                            , HttpStatus.BAD_REQUEST);
                }
            } else {
                throw new CustomException(
                        reservationDto.getReservationTime() + "에 예약한 "
                                + reservationDto.getRestaurantName() + "에 대한 예약이 존재합니다. "
                                + "예약 요청에 실패했습니다."
                        , HttpStatus.BAD_REQUEST);
            }
        }*/

        // user 테이블 조회
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new CustomException("해당 사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        // restaurant 테이블 조회
        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new CustomException("해당 식당을 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        BlackList blackList = blackListRepository.findByRestaurantIdAndUserId(req.getRestaurantId(), req.getUserId())
                .orElse(null);

        // 사용자 블랙리스트 여부 확인
        if (blackList != null) {
            throw new CustomException("해당 식당의 블랙리스트에 등록되어 주문할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        // 식당 영업 상태 확인
        if (restaurant.getStatus() != 0) {
            throw new CustomException("해당 식당이 영업중이 아닙니다.", HttpStatus.BAD_REQUEST);
        }

        Admin admin = adminRepository.findByDivisionId(req.getRestaurantId())
                .orElseThrow(() -> new CustomException("해당 관리자 정보가 없습니다.", HttpStatus.NOT_FOUND));

        // 식당 비활성화 상태 확인
        if (admin.getCode().getCode().equals("00101") && admin.getCoalitionState() != 0) {
            throw new CustomException("해당 식당이 비활성화 상태입니다.", HttpStatus.BAD_REQUEST);
        }

        // 해당 유저의 진행 중인 결제 확인
        List<Order> existingOrder = orderRepository.findByUserId(user);
        if (!existingOrder.isEmpty()) {
            for (Order order : existingOrder) {
                // 예약 상태가 0 또는 1인 경우에 대해서만 처리
                if (order.getReservationStatus() == 0 || order.getReservationStatus() == 1) {
                    // 해당 주문에 연결된 티켓을 조회
                    Optional<Ticket> ticketOpt = ticketRepository.findByOrderOrderId(order.getOrderId());

                    // 티켓이 없으면 예외 발생
                    if (ticketOpt.isEmpty()) {
                        throw new CustomException("이미 진행중인 결제를 완료하기 전에는 새로운 주문을 할 수 없습니다.", HttpStatus.BAD_REQUEST);
                    }

                    // 티켓이 있는 경우, 티켓 상태가 완료(1)가 아니면 예외 발생
                    Ticket ticket = ticketOpt.get();
                    if (ticket.getTicketStatus() != 1) {
                        throw new CustomException("이미 진행중인 결제를 완료하기 전에는 새로운 주문을 할 수 없습니다.", HttpStatus.BAD_REQUEST);
                    }
                }
            }
        }

        // 주문 정보 생성
        Order order = new Order();
        order.setUserId(user);
        order.setRestaurantId(restaurant);
        order.setReservationYn(1);
        orderRepository.save(order);
        orderRepository.flush();

        // 주문상세정보 생성
        for (ReservationMenuDto reservationMenuDto : req.getMenuList()) {
            RestaurantMenu restaurantMenu = restaurantMenuRepository.findById(reservationMenuDto.getMenuId())
                            .orElseThrow(() -> new CustomException("해당 메뉴를 조회할 수 없습니다.", HttpStatus.NOT_FOUND));

            RestaurantMenu selRestaurantMenu = restaurantMenuRepository.findById(restaurantMenu.getMenuId())
                    .orElseThrow(() -> new CustomException("해당 메뉴를 조회할 수 없습니다.", HttpStatus.NOT_FOUND));

            RestaurantMenuCategory restaurantMenuCategory = restaurantMenuCategoryRepository.findById(selRestaurantMenu.getCategoryId().getCategoryId())
                    .orElseThrow(() -> new CustomException("해당 카테고리를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

            if(!(restaurantMenuCategory.getRestaurant().getRestaurantId().equals(req.getRestaurantId()))) {
                throw new CustomException("해당 식당의 메뉴가 아닙니다.", HttpStatus.BAD_REQUEST);
            }

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order);
            orderDetail.setMenuId(restaurantMenu);
            orderDetail.setMenuCount(reservationMenuDto.getMenuCount());
            orderDetail.setPrice(restaurantMenu.getPrice());
            orderDetailRepository.save(orderDetail);
        }

        // 예약 정보 생성
        Reservation reservation = new Reservation();
        reservation.setOrder(order);
        reservation.setReservationTime(req.getReservationTime());
        reservation.setReservationPeopleCount(req.getReservationPeopleCount());
        reservation.setUserPhone(req.getUserPhone());
        reservationRepository.save(reservation);

        ReservationInsDto reservationInsDto = new ReservationInsDto();

        reservationInsDto.setOrderId(order.getOrderId());
        reservationInsDto.setReservationPeopleCount(req.getReservationPeopleCount());
        reservationInsDto.setReservationTime(req.getReservationTime());
        reservationInsDto.setUserPhone(req.getUserPhone());

        // meal_time 테이블 insert
        MealTime mealTime = new MealTime();
        mealTime.setOrderId(order);
        mealTime.setStartMealDate(reservation.getReservationTime());
        mealTime.setRestaurantId(restaurant);
        mealTimeRepository.save(mealTime);
        mealTimeRepository.flush();

        // 예약 10분 뒤 업데이트 안할 시 취소 처리할 스케줄러 실행
        reservationScheduler.scheduleCancellation(reservationInsDto.getReservationId());

        // 5분 지난뒤 5분 남았다고 알림
        scheduler.schedule(() -> {
            Map<String, String> messageObject = new HashMap<>();

            messageObject.put("message", "예약시간: " + req.getReservationTime() + "에 대한 예약에 대한 자동 취소가 5분 남았습니다. 예약을 완료해 주세요.");
            messageObject.put("orderId", String.valueOf(order.getOrderId()));

            messagingTemplate.convertAndSend(
                    "/queue/reservation/" + order.getOrderId() + "/user/reservation",
                    messageObject
            );

        }, 5, TimeUnit.MINUTES);

        // 생성된 예약 정보 가져옴(소켓 통신으로 보내줌.)
        ReservationDto createdReservationDto = reservationMapper.selReservationByReservationId(reservationInsDto.getReservationId());

        // 사장님 구독 경로로 예약 알림 메시지 전송
        ReservationMessageRes reservationMessageRes = new ReservationMessageRes();
        reservationMessageRes.setOrderId(order.getOrderId());
        reservationMessageRes.setReservationPeopleCount(req.getReservationPeopleCount());
        reservationMessageRes.setReservationTime(req.getReservationTime());
        reservationMessageRes.setUserPhone(req.getUserPhone());
        reservationMessageRes.setRestaurantId(restaurant.getRestaurantId());
        reservationMessageRes.setUserId(user.getUserId());
        reservationMessageRes.setMenuList(req.getMenuList());
        reservationMessageRes.setTypeMessage("예약 요청");

        messagingTemplate.convertAndSend(
                "/queue/restaurant/" + req.getRestaurantId() + "/owner/reservation",
                req
        );

        // 일단 무조건 승인 되었다고 처리함.
            /*OrderAccessPatchReq p = new OrderAccessPatchReq();
            p.setOrderId(createdOrderId);
            p.setReservationStatus(1);

            result = orderService.updOrderAccess(p);*/

        return 1;
    }
}