package com.green.attaparunever2.admin.company.model;

import com.green.attaparunever2.common.model.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class SelPurchaseHistoryReq extends Paging {
    @Schema(description = "회사 PK", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long companyId;

    public SelPurchaseHistoryReq(Integer page, Integer size, Long companyId) {
        super(page, size);
        this.companyId = companyId;
    }
}
