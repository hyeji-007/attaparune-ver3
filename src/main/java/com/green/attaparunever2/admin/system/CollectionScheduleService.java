package com.green.attaparunever2.admin.system;

import com.green.attaparunever2.entity.SettlementList;
import com.green.attaparunever2.entity.Ticket;
import com.green.attaparunever2.order.ticket.TicketMapper;
import com.green.attaparunever2.order.ticket.TicketRepository;
import com.green.attaparunever2.order.ticket.model.SelTicketDto;
import com.green.attaparunever2.order.ticket.model.TicketSelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
@EnableScheduling
public class CollectionScheduleService {
    private final ThreadPoolTaskScheduler taskScheduler;
    private ScheduledFuture<?> scheduledFuture;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private SettlementListRepository settlementListRepository;
    @Autowired
    private TicketRepository ticketRepository;

    // ThreadPoolTaskScheduler를 직접 초기화
    public CollectionScheduleService() {
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.initialize();
    }

    // 새로운 요일로 스케줄을 설정하는 메서드
    public void scheduleTaskForDay(String day) {
        // 기존에 설정된 스케줄이 있으면 취소
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(false);
        }

        // 새로운 크론 표현식 계산
        String cronExpression = getCronExpressionForDay(day);

        // 새로운 스케줄 작업 등록
        scheduledFuture = taskScheduler.schedule(this::processDeposits, new CronTrigger(cronExpression));
        System.out.println("Scheduled task for day " + day);
    }

    private String getCronExpressionForDay(String day) {
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(day.toUpperCase());

        // 요일에 따른 Cron 표현식 생성 (예: 화요일은 TUE, 월요일은 MON)
        Map<DayOfWeek, String> dayToCron = new HashMap<>();
        dayToCron.put(DayOfWeek.MONDAY, "0 0 17 ? * MON");
        dayToCron.put(DayOfWeek.TUESDAY, "0 0 17 ? * TUE");
        dayToCron.put(DayOfWeek.WEDNESDAY, "0 0 17 ? * WED");
        dayToCron.put(DayOfWeek.THURSDAY, "0 0 17 ? * THU");
        dayToCron.put(DayOfWeek.FRIDAY, "0 0 17 ? * FRI");
        dayToCron.put(DayOfWeek.SATURDAY, "0 0 17 ? * SAT");
        dayToCron.put(DayOfWeek.SUNDAY, "0 0 17 ? * SUN");

        return dayToCron.getOrDefault(dayOfWeek, "0 0 17 ? * MON");
    }

    // 입금 처리 로직
    @Transactional
    public void processDeposits() {
        System.out.println("입금처리를 시작합니다.");

        // 결재 처리 되지 않은 식권 목록 가져옴
        List<TicketSelDto> dtoList = ticketMapper.selTicketNotInSettlementList();

        for(TicketSelDto dto : dtoList) {
            Ticket ticket = ticketRepository.findById(dto.getTicketId()).orElseThrow();

            SettlementList settlement = new SettlementList();
            settlement.setTicket(ticket);

            settlementListRepository.save(settlement);
        }
    }
}
