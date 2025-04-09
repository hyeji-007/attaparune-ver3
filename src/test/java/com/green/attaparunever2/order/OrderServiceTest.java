package com.green.attaparunever2.order;

import com.green.attaparunever2.admin.AdminRepository;
import com.green.attaparunever2.admin.restaurant.BlackListRepository;
import com.green.attaparunever2.entity.*;
import com.green.attaparunever2.order.model.OrderDetailPostReq;
import com.green.attaparunever2.order.model.OrderPostReq;
import com.green.attaparunever2.order.ticket.MealTimeRepository;
import com.green.attaparunever2.order.ticket.TicketRepository;
import com.green.attaparunever2.restaurant.RestaurantRepository;
import com.green.attaparunever2.restaurant.restaurant_menu.RestaurantMenuRepository;
import com.green.attaparunever2.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @InjectMocks private OrderService orderService;
    @Mock private UserRepository userRepository;
    @Mock private RestaurantRepository restaurantRepository;
    @Mock private BlackListRepository blackListRepository;
    @Mock private AdminRepository adminRepository;
    @Mock private OrderRepository orderRepository;
    @Mock private TicketRepository ticketRepository;
    @Mock private MealTimeRepository mealTimeRepository;
    @Mock private OrderDetailRepository orderDetailRepository;
    @Mock private RestaurantMenuRepository restaurantMenuRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // 💡 Mock 초기화
    }


    private final long SIGNED_USER_ID = 1L;
    private final long RESTAURANT_ID = 1L;
    private final long ADMIN_ID = 1L;
    private final long MENU_ID = 1L;
    private final int COUNT = 2;
    private final int STATUS = 0;

    @Test
    @DisplayName("주문등록")
    void postOrderWithDetail() {
        // 🛠 Given: 요청 객체 생성
        OrderPostReq givenParam = new OrderPostReq();
        givenParam.setUserId(SIGNED_USER_ID);
        givenParam.setRestaurantId(RESTAURANT_ID);

        OrderDetailPostReq givenDetail = new OrderDetailPostReq();
        givenDetail.setMenuId(MENU_ID);
        givenDetail.setMenuCount(COUNT);

        givenParam.setOrderDetails(List.of(givenDetail));

        // 🛠 Given: Mock 객체 생성
        User mockUser = new User();
        mockUser.setUserId(SIGNED_USER_ID);
        mockUser.setName("테스트 유저");

        Restaurant mockRestaurant = new Restaurant();
        mockRestaurant.setRestaurantId(RESTAURANT_ID);
        mockRestaurant.setRestaurantName("테스트 식당");
        mockRestaurant.setStatus(STATUS);

        Code mockCode = new Code();
        mockCode.setCode("00101");

        Admin mockAdmin = new Admin();
        mockAdmin.setAdminId(ADMIN_ID);
        mockAdmin.setDivisionId(mockRestaurant.getRestaurantId());
        mockAdmin.setCoalitionState(STATUS);
        mockAdmin.setCode(mockCode);

        RestaurantMenu mockMenu = new RestaurantMenu();
        mockMenu.setMenuId(MENU_ID);
        mockMenu.setMenuName("테스트 메뉴");
        mockMenu.setPrice(15000);

        Order mockOrder = new Order();
        mockOrder.setUserId(mockUser);
        mockOrder.setRestaurantId(mockRestaurant);

        MealTime mockMealTime = new MealTime();
        mockMealTime.setOrderId(mockOrder);
        mockMealTime.setRestaurantId(mockRestaurant);
        mockMealTime.setStartMealDate(LocalDateTime.now());

        // 🛠 When: Mock Repository Behavior 정의
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order savedOrder = invocation.getArgument(0);
            savedOrder.setOrderId(1L); // 가짜 PK 설정
            return savedOrder;
        });

        when(mealTimeRepository.save(any(MealTime.class))).thenAnswer(invocation -> invocation.getArgument(0));

        when(userRepository.findByUserId(SIGNED_USER_ID)).thenReturn(Optional.of(mockUser));
        when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.of(mockRestaurant));
        when(blackListRepository.findByRestaurantIdAndUserId(RESTAURANT_ID, SIGNED_USER_ID)).thenReturn(Optional.empty());
        when(adminRepository.findByDivisionId(RESTAURANT_ID)).thenReturn(Optional.of(mockAdmin));
        when(orderRepository.findByRestaurantIdAndUserId(mockRestaurant, mockUser)).thenReturn(Collections.emptyList());
        when(restaurantMenuRepository.findById(MENU_ID)).thenReturn(Optional.of(mockMenu));

        // 🛠 When: 주문 등록 실행
        long orderId = orderService.postOrderWithDetail(givenParam);

        // ✅ Then: 검증
        assertEquals(1L, orderId);
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(mealTimeRepository, times(1)).save(any(MealTime.class));
        verify(orderDetailRepository, times(1)).save(argThat(orderDetail -> orderDetail.getOrderId().getOrderId() == 1L));
    }

}