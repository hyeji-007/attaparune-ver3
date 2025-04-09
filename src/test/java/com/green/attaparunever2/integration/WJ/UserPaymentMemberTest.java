package com.green.attaparunever2.integration.WJ;


import com.green.attaparunever2.integration.BaseIntegrationTest;
import com.green.attaparunever2.user.user_payment_member.model.PaymentMember;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserPaymentMemberTest extends BaseIntegrationTest {
    //결제 승인 요청([POST]api/user/user-payment-member)
    @Test
    public void postPaymentMember() throws Exception {
        HashMap<String, Object> req = new HashMap<>();
        req.put("orderId", 1L);

        List<PaymentMember> memberList = new ArrayList<>();

        PaymentMember member = new PaymentMember();
        member.setUserId(1L);
        member.setPoint(10000);

        memberList.add(member);

        req.put("data", memberList);

        String requestBody = objectMapper.writeValueAsString(req);

        mvc.perform(post("/api/user/user-payment-member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk());

    }

    // 내개온 결제 승인 요청 처리 ([PATCH] /api/user/user-payment-member)
    @Test
    public void patchPaymentMember() throws Exception {
        HashMap<String, Object> req = new HashMap<>();
        req.put("orderId", 1L);
        req.put("userId", 1L);
        req.put("approvalStatus", 1);

        String requestBody = objectMapper.writeValueAsString(req);

        mvc.perform(patch("/api/user/user-payment-member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // 티켓 생성 ([POST] /api/user/user-payment-member/insTicket)
    @Test
    public void postTicket() throws Exception {
        HashMap<String, Object> req = new HashMap<>();
        req.put("orderId", 1L);

        String requestBody = objectMapper.writeValueAsString(req);

        mvc.perform(post("/api/user/user-payment-member/insTicket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
