package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.order.ticket.model.TicketGetReq;
import com.green.attaparunever2.order.ticket.model.TicketGetRes;
import com.green.attaparunever2.order.ticket.model.TicketPostReq;
import com.green.attaparunever2.order.ticket.model.TicketUpdReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("order/ticket")
@Tag(name = "식권", description = "식권 관리")
public class TicketController {
    private final TicketService service;

    @GetMapping
    @Operation(summary = "식권 조회")
    public ResultResponse<TicketGetRes> getTicket(@Valid @RequestParam long ticketId) {
        TicketGetReq req = new TicketGetReq(ticketId);
        TicketGetRes res = service.getTicket(req);
        return ResultResponse.<TicketGetRes>builder()
                .statusCode("200")
                .resultMsg("식권 조회 완료")
                .resultData(res)
                .build();
    }

    @PatchMapping
    @Operation(summary = "식권 사용 여부 변경", description = "식권 사용 완료 처리")
    public ResultResponse<Integer> patchTicket(@RequestBody TicketUpdReq req) {
        int result = service.updTicket(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("식권 사용 완료")
                .resultData(result)
                .build();
    }

    @GetMapping("ticketOne")
    @Operation(summary = "유저 PK로 최신 티켓 PK 받기")
    public ResultResponse<Long> getTicketOne(long userId) {
        long result = service.getTicketOne(userId);

        return ResultResponse.<Long>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("티켓 PK 받기")
                .resultData(result)
                .build();
    }
}