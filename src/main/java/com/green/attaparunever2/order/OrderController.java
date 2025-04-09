package com.green.attaparunever2.order;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.order.model.*;
import com.green.attaparunever2.order.ticket.TicketService;
import com.green.attaparunever2.order.ticket.model.TicketPostReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
@Tag(name = "주문", description = "주문 관리")
public class OrderController {
    private final OrderService service;

    @PostMapping("/with-detail")
    @Operation(summary = "주문 정보 등록")
    public ResultResponse<Long> postOrderWithDetail(@Valid @RequestBody OrderPostReq p, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultResponse.<Long>builder()
                    .statusCode("400")
                    .resultMsg("주문 정보 등록 실패")
                    .resultData(0L)
                    .build();
        }

        long result = service.postOrderWithDetail(p);
        return ResultResponse.<Long>builder()
                .statusCode("200")
                .resultMsg("주문 정보 등록 완료")
                .resultData(result)
                .build();
    }
}
