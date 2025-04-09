package com.green.attaparunever2.integration.WJ;

import com.green.attaparunever2.integration.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReservationTest extends BaseIntegrationTest {
    //주문 상태 변경 테스트

    @Test
    void updOrderAccess() throws Exception {
        HashMap<String, Object> req = new HashMap<>();
        req.put("orderId", 1);
        req.put("reservationStatus", 1);

        String requestBody = objectMapper.writeValueAsString(req);

        mvc.perform(put("/api/admin/restaurant/order/reservation/status")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isOk());
    }
}
