package com.green.attaparunever2.integration.WJ;

import com.green.attaparunever2.integration.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Objects;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TicketTest extends BaseIntegrationTest {
    // 식권 사용 여부 변경 ([PATCH] /api/order/ticket)
    @Test
    void patchTicket() throws Exception {
        HashMap<String, Object> req = new HashMap<>();
        req.put("ticketId", 1);
        req.put("restaurantId", 1);
        req.put("paymentPassword", 123456);

        String requestBody = objectMapper.writeValueAsString(req);

        mvc.perform(patch("/api/order/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
