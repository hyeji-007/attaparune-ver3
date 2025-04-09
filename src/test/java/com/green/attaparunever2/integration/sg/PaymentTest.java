package com.green.attaparunever2.integration.sg;

import com.green.attaparunever2.integration.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PaymentTest extends BaseIntegrationTest {
    // 결재전 결재 정보 임시 저장([POST]/api/admin/company/v3/payment/temp)
    @Test
    void paymentTempTest() throws Exception {
        // Given
        HashMap<String, Object> req = new HashMap<>();

        final String TOSS_PAYMENT_ORDER_ID = "aeb80587-7d2e-4c4b-9835-a1257164d4b7"; // 실제 프론트에서 결재요청할 orderId값
        final int AMOUNT = 50000; // 실제 프론트에서 결재요청할 금액

        req.put("orderId", TOSS_PAYMENT_ORDER_ID);
        req.put("amount", AMOUNT);

        String requestBody = objectMapper.writeValueAsString(req);

        // When & Then
        mvc.perform(post("/api/admin/company/v3/payment/temp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())  // 👈 요청 & 응답 상세 내용을 출력
                .andExpect(status().isOk());
    }

    // 포인트 구매([POST]/api/admin/company/v3/point)
    @Test
    void paymentTest() throws Exception {
        // Given
        final String PAYMENT_KEY = "tgen_20250312131110fdLW4"; // 실제 프론트에서 요청 성공한 KEY
        final String TOSS_PAYMENT_ORDER_ID = "42ea2628-7f7f-450b-b090-f9c50abf233c"; // 실제 프론트에서 결재요청 성공한 orderId값
        final int AMOUNT = 50000; // 실제 프론트에서 결재요청 성공한 금액

        // JSON 문자열 생성
        String jsonBody = "{ \"paymentKey\": \"" + PAYMENT_KEY + "\", \"orderId\": \"" + TOSS_PAYMENT_ORDER_ID +"\"" +
                ", \"amount\": "+AMOUNT+" }";

        // When & Then
        mvc.perform(post("/api/admin/company/v3/point")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andDo(print())  // 👈 요청 & 응답 상세 내용을 출력
                .andExpect(status().isOk());
    }

    // 환불요청([POST]/api/admin/company/v3/refund)
    @Test
    void refundTest() throws Exception {
        // Given
        final long ADMIN_ID = 1L;
        final int REFUND_POINT = 100000;
        final String REFUND_DETAIL = "회사폐업";
        final String JWT = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJncmVlbkBncmVlbi5rciIsImlhdCI6MTc0MTc2MTE2MSwiZXhwIjoxNzQxODQ3NTYxLCJzaWduZWRVc2VyIjoie1wic2lnbmVkVXNlcklkXCI6MSxcInJvbGVzXCI6XCJST0xFX0NPTVBBTllcIn0ifQ.Wp0DydaqdEB-1pKna51y4CE2SfUjp90gS7RqxH-iQB4";
        HashMap<String, Object> req = new HashMap<>();

        req.put("adminId", ADMIN_ID); // 실제 DB에 존제하는
        req.put("refundPoint", REFUND_POINT);
        req.put("refundDetail", REFUND_DETAIL);

        String requestBody = objectMapper.writeValueAsString(req);

        // When & Then
        mvc.perform(post("/api/admin/company/v3/refund")
                        .header("Authorization", "Bearer " + JWT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())  // 👈 요청 & 응답 상세 내용을 출력
                .andExpect(status().isOk());
    }
}
