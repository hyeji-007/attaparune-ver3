package com.green.attaparunever2.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.attaparunever2.config.constant.JwtConst;
import com.green.attaparunever2.config.jwt.JwtTokenProvider;
import com.green.attaparunever2.order.OrderService;
import com.green.attaparunever2.order.model.OrderPostReq;
import com.green.attaparunever2.reservation.ReservationService;
import com.green.attaparunever2.user.user_payment_member.UserPaymentMemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(
        value = UserController.class
)
class UserControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private OrderService orderService;
    @MockBean private UserPaymentMemberService userPaymentMemberService;
    @MockBean private JwtTokenProvider jwtTokenProvider;
    @MockBean private JwtConst jwtConst;
    @MockBean private UserService userService;
    @MockBean private ReviewService reviewService;
    @MockBean private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mock 초기화

    }

    private final long SIGNED_USER_ID = 1L;
    private final long RESTAURANT_ID = 1L;
    private final long TEST_ORDER_ID = 1L;

    @Test
    @DisplayName("주문 정보 등록")
    void postOrderWithDetail() throws Exception {
        // Given: 요청 객체 생성
        OrderPostReq givenParam = new OrderPostReq();
        givenParam.setUserId(SIGNED_USER_ID);
        givenParam.setRestaurantId(RESTAURANT_ID);

        // Given: 주문 등록 성공 시 서비스 메서드 호출 반환값 설정
        when(orderService.postOrderWithDetail(any(OrderPostReq.class))).thenReturn(TEST_ORDER_ID);

        // When & Then: 컨트롤러 테스트
        mockMvc.perform(post("/user/order") // /user/order POST 요청
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(givenParam))) // JSON 형식의 요청 본문
                .andExpect(status().isOk()) // HTTP 상태 200
                .andExpect(jsonPath("$.statusCode").value("200")) // 성공 메시지 확인
                .andExpect(jsonPath("$.resultMsg").value("주문 정보 등록 완료"))
                .andExpect(jsonPath("$.resultData").value(TEST_ORDER_ID)); // 결과 값 확인

        // Verify: 서비스 메서드 호출 여부 검증
        verify(orderService, times(1)).postOrderWithDetail(any(OrderPostReq.class));
    }
}