package com.green.attaparunever2.user.user_payment_member.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserPostPaymentMemberReq {
    @Schema(title = "주문 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long orderId;

    @Schema(title = "결제 승인 사용자 리스트", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<PaymentMember> data;  // 사용자와 포인트를 포함하는 객체 리스트

    @JsonIgnore
    @Schema(title = "승인 여부", description = "0:미승인, 1:승인, 2:거부")
    private List<Integer> approvalStatus;
}
