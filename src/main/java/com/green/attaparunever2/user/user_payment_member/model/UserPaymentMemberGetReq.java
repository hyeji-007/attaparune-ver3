package com.green.attaparunever2.user.user_payment_member.model;

import com.green.attaparunever2.common.model.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Schema(title = "결제 인원 조회 요청")
@ToString
public class UserPaymentMemberGetReq extends Paging {
    @Schema(description = "회사 PK", requiredMode = Schema.RequiredMode.REQUIRED)
    private long companyId;

    public UserPaymentMemberGetReq(Integer page, Integer size, long companyId) {
        super(page, size);
        this.companyId = companyId;
    }
}
