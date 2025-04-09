package com.green.attaparunever2.user.user_payment_member.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserGetPaymentInfoRes {
    @Schema(title = "결제금액")
    private int point;
    @Schema(title = "식당명")
    private String restaurantName;
    @Schema(title = "요청 받는 사람")
    private String name;
    @Schema(title = "요청 보낸 사람")
    private String orderUserName;
}
