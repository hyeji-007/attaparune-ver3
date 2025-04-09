package com.green.attaparunever2.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderAccessMessageRes {
    private long orderId;
    private int reservationStatus; //0:미승인, 1:승인, 2:거부, 3:취소
    private LocalDateTime createdAt;
    private String typeMessage;
}
