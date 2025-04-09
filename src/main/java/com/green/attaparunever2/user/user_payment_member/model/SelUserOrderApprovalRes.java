package com.green.attaparunever2.user.user_payment_member.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelUserOrderApprovalRes {
    @JsonIgnore
    private long userId;
    @Schema(title = "사용자 이름")
    private String name;
    @Schema(title = "사용자 아이디")
    private String uid;
    @Schema(title = "예약 상태")
    private int approvalStatus;
    @Schema(title = "승인 포인트")
    private int point;
    @JsonIgnore
    private int userPoint;
}
