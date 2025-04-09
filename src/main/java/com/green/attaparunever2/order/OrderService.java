package com.green.attaparunever2.order;

import com.green.attaparunever2.admin.AdminRepository;
import com.green.attaparunever2.admin.restaurant.BlackListRepository;
import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.config.security.AuthenticationFacade;
import com.green.attaparunever2.entity.*;
import com.green.attaparunever2.order.model.*;
import com.green.attaparunever2.order.ticket.MealTimeRepository;
import com.green.attaparunever2.order.ticket.TicketRepository;
import com.green.attaparunever2.restaurant.RestaurantRepository;
import com.green.attaparunever2.restaurant.restaurant_menu.RestaurantMenuRepository;
import com.green.attaparunever2.user.UserRepository;
import com.green.attaparunever2.user.user_payment_member.UserPaymentMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper mapper;
    private final SimpMessagingTemplate messagingTemplate;
    private final AuthenticationFacade authenticationFacade;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final MealTimeRepository mealTimeRepository;
    private final UserPaymentMemberRepository userPaymentMemberRepository;
    private final BlackListRepository blackListRepository;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final TicketRepository ticketRepository;
    private final RestaurantMenuRepository restaurantMenuRepository;


    public long postOrder(OrderPostReq p) {
        return mapper.postOrder(p);
    }

    public int postOrderDetail(OrderDetailPostReq p) {
        return mapper.postOrderDetail(p);
    }

    @Transactional
    public long postOrderWithDetail(OrderPostReq p) {
        User user = userRepository.findByUserId(p.getUserId())
                .orElseThrow(() -> new CustomException("해당 유저가 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(p.getRestaurantId())
                .orElseThrow(() -> new CustomException("해당 식당이 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        BlackList blackList = blackListRepository.findByRestaurantIdAndUserId(p.getRestaurantId(), p.getUserId())
                .orElse(null);

        // 사용자 블랙리스트 여부 확인
        if (blackList != null) {
            throw new CustomException("해당 식당의 블랙리스트에 등록되어 주문할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        // 식당 영업 상태 확인
        if (restaurant.getStatus() != 0) {
            throw new CustomException("해당 식당이 영업중이 아닙니다.", HttpStatus.BAD_REQUEST);
        }

        Admin admin = adminRepository.findByDivisionId(p.getRestaurantId())
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

        // Order 테이블 insert
        Order order = new Order();
        order.setUserId(user);
        order.setRestaurantId(restaurant);

        orderRepository.save(order);
        orderRepository.flush();

        // Meal_Time 테이블 insert
        MealTime mealTime = new MealTime();
        mealTime.setOrderId(order);
        mealTime.setStartMealDate(order.getCreatedAt());
        mealTime.setRestaurantId(restaurant);

        mealTimeRepository.save(mealTime);
        mealTimeRepository.flush();


        for (OrderDetailPostReq detailReq : p.getOrderDetails()) {
            RestaurantMenu restaurantMenu = restaurantMenuRepository.findById(detailReq.getMenuId()).orElseThrow(() -> new CustomException("존재하지 않는 메뉴 입니다.", HttpStatus.BAD_REQUEST));
            restaurantMenu.setMenuId(detailReq.getMenuId());

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order);
            orderDetail.setMenuId(restaurantMenu);
            orderDetail.setMenuCount(detailReq.getMenuCount());
            orderDetail.setPrice(restaurantMenu.getPrice());
            orderDetailRepository.save(orderDetail);
        }

        return 1;
    }

    public int updOrderAccess(OrderAccessPatchReq p) {
        // 사용자에게 예약결과 알림 설정
        Order order = orderRepository.findById(p.getOrderId())
                .orElseThrow(() -> new CustomException("해당 주문을 찾을 수 없습니다", HttpStatus.BAD_REQUEST));

        order.setReservationStatus(p.getReservationStatus());
        orderRepository.save(order);
        orderRepository.flush();
        OrderAccessMessageRes res = new OrderAccessMessageRes();
        res.setOrderId(order.getOrderId());
        res.setCreatedAt(order.getCreatedAt());
        res.setReservationStatus(order.getReservationStatus());
        res.setTypeMessage("식당에서의 예약 승인, 거부 여부");
        messagingTemplate.convertAndSend(
                "/queue/reservation/" + order.getOrderId() + "/user/reservation",
                res
        );

        return 1;
    }

    public GetOrderRes getOrder(OrderGetReq p) {
        GetOrderRes res = mapper.getOrder(p);
        List<GetOrderDto> list = mapper.getOrderList(p);

        res.setOrderDtoList(list);

        return res;

    }

    // 예약 주문 목록
    public List<RestaurantOrderDto> getReservationOrder(OrderListSelReq p) {
        List<RestaurantOrderDto> orderList = mapper.selReservationOrderListByRestaurantId(p.getRestaurantId());

        for(RestaurantOrderDto order : orderList) {
            order.setOrderDetails(mapper.selOrderDetailByOrderId(order.getOrderId()));
        }

        return orderList;
    }

    // 예약완료 주문 목록(미결재)
    public List<RestaurantOrderDto> getCompleteOrder(OrderListSelReq p) {
        List<RestaurantOrderDto> orderList = mapper.selCompleteOrderListByRestaurantId(p.getRestaurantId());

        for(RestaurantOrderDto order : orderList) {
            order.setOrderDetails(mapper.selOrderDetailByOrderId(order.getOrderId()));
        }

        return orderList;
    }
}
