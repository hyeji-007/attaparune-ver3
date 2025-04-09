package com.green.attaparunever2.admin.model;

import com.green.attaparunever2.common.model.Paging;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SelCompanyPaymentReq extends Paging {
    public SelCompanyPaymentReq(Integer page, Integer size) {
        super(page, size);
    }
}
