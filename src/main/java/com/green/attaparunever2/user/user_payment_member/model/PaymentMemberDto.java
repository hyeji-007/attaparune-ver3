package com.green.attaparunever2.user.user_payment_member.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentMemberDto {
    @Schema(title = "사용자 이름")
    private String name;
    @Schema(title = "사용자 아이디")
    private String uid;
    @Schema(title = "사용자 PK")
    private long userId;
}
