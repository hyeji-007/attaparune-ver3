package com.green.attaparunever2.user.user_payment_member;

import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.entity.Order;
import com.green.attaparunever2.entity.User;
import com.green.attaparunever2.entity.UserPaymentMember;
import com.green.attaparunever2.entity.UserPaymentMemberIds;
import com.green.attaparunever2.order.OrderMapper;
import com.green.attaparunever2.order.model.OrderSelDto;
import com.green.attaparunever2.user.user_payment_member.model.PaymentMember;
import com.green.attaparunever2.user.user_payment_member.model.UserGetPointRes;
import com.green.attaparunever2.user.user_payment_member.model.UserPostPaymentMemberReq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class UserPaymentMemberServiceTest {

    @InjectMocks UserPaymentMemberService userPaymentMemberService;

    @Mock private OrderMapper orderMapper;
    @Mock private UserPaymentMemberRepository userPaymentMemberRepository;
    @Mock private SimpMessagingTemplate messagingTemplate;
    @Mock private UserPaymentMemberRepository orderRepository;
    @Mock private UserPaymentMemberMapper userPaymentMemberMapper;

    @Test
    public void shouldProcessPaymentRequestSuccessfully() {
        // Given
        Long orderId = 1L;
        Long userId = 100L;
        int requestPoint = 5000;



        UserPostPaymentMemberReq request = new UserPostPaymentMemberReq();
        request.setOrderId(orderId);

        PaymentMember paymentMember = new PaymentMember();
        paymentMember.setUserId(userId);
        paymentMember.setPoint(requestPoint);
        request.setData(List.of(paymentMember));

        OrderSelDto orderSelDto = new OrderSelDto();
        orderSelDto.setUserId(userId);

        UserGetPointRes userPointRes = new UserGetPointRes();
        userPointRes.setPoint(10000); // 사용자의 보유 포인트

        // Mock 설정
        when(orderMapper.selOrderByOrderId(orderId)).thenReturn(orderSelDto);
        when(userPaymentMemberMapper.getPoint(userId)).thenReturn(userPointRes);
        when(userPaymentMemberMapper.selUserPaymentMemberCount(orderId, userId)).thenReturn(0);

        // When
        int result = userPaymentMemberService.postPaymentMember(request);

        // Then
        assertEquals(1, result, "결제 요청이 정상적으로 처리되어야 한다.");
        verify(userPaymentMemberRepository, times(1)).save(any(UserPaymentMember.class));
        verify(userPaymentMemberRepository, times(1)).flush();
    }

}