package com.green.attaparunever2.integration.sg;

import com.green.attaparunever2.integration.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RefundTest extends BaseIntegrationTest {
    // 포인트 구매([PATCH]/api/admin/system/v3/refund)
    @Test
    void reservationPostTest() throws Exception {
        // Given
        final long REFUND_ID = 3L;
        final int REFUND_YN = 1;

        HashMap<String, Object> req = new HashMap<>();

        req.put("refundId", REFUND_ID);
        req.put("refundYn", REFUND_YN);

        String requestBody = objectMapper.writeValueAsString(req);

        // When & Then
        mvc.perform(patch("/api/admin/system/v3/refund")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())  // 👈 요청 & 응답 상세 내용을 출력
                .andExpect(status().isOk());

    }
}
