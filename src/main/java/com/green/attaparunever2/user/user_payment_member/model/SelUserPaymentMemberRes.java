package com.green.attaparunever2.user.user_payment_member.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelUserPaymentMemberRes {
    @Schema(title = "사용자 이름")
    private String name;
    @Schema(title = "사용자 ID")
    private String uid;
    @Schema(title = "승인 상태")
    private int approvalStatus;
    @Schema(title = "승인 포인트")
    private int point;
    @Schema(title = "사용자 PK")
    private long userId;
}
