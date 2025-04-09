package com.green.attaparunever2.admin.model;

import com.green.attaparunever2.common.model.Paging;
import lombok.Getter;

@Getter
public class GetRefundReq extends Paging {
    public GetRefundReq(Integer page, Integer size) {
        super(page, size);
    }
}
