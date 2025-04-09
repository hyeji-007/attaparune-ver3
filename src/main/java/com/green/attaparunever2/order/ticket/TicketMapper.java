package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.order.ticket.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TicketMapper {
    int postTicket(TicketPostReq p);
    TicketDto getTicket(long ticketId);
    int delTicket(TicketDelReq req);
    TicketSelDto selTicketByOrderId(long orderId);
    int updTicket(long ticketId);
    TicketUseDateSelRes selTicketUseDate(long ticketId);
    SelTicketDto selTicketByTicketId(long ticketId);
    long selTicketOne(long userId);

    // 결재안된 티켓 리스트 조회
    List<TicketSelDto> selTicketNotInSettlementList();
}
