package com.green.attaparunever2.user.user_payment_member.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(title = "결제 인원 조회 응답")
public class UserPaymentMemberGetRes {

    @Schema(title = "결제 인원 리스트")
    private List<PaymentMemberDto> memberList;
}
