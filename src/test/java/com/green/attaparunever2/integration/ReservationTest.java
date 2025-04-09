package com.green.attaparunever2.integration;

import com.green.attaparunever2.reservation.model.ReservationMenuDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; // status 검증 매처
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print; // 요청 & 응답 정보 출력

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReservationTest extends BaseIntegrationTest{
    // 예약요청([POST]/api/reservation)
    @Test
    void reservationPostTest() throws Exception {
        // Given
        HashMap<String, Object> req = new HashMap<>();
        //TestReservationPostReq req = new TestReservationPostReq();

        req.put("userId", 3);
        req.put("restaurantId", 1);
        req.put("reservationTime", "2025-03-11T16:59:00");
        req.put("reservationPeopleCount", 2);
        req.put("userPhone", "01000000000");
        /*req.setUserId(3);
        req.setRestaurantId(1);
        LocalDateTime time = LocalDateTime.now();
        req.setReservationTime(time);
        req.setReservationPeopleCount(2);
        req.setUserPhone("01000000000");*/

        List<ReservationMenuDto> menuList = new ArrayList<ReservationMenuDto>();

        int[] menuCdList = {26, 62, 60};

        for(int i=0; i<3; i++) {
            ReservationMenuDto menu = new ReservationMenuDto();
            menu.setMenuId(menuCdList[i]);
            menu.setMenuCount(1);
            menuList.add(menu);
        }

        req.put("menuList", menuList);

        String requestBody = objectMapper.writeValueAsString(req);

        // When & Then
        mvc.perform(post("/api/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())  // 👈 요청 & 응답 상세 내용을 출력
                .andExpect(status().isOk());

    }
}
