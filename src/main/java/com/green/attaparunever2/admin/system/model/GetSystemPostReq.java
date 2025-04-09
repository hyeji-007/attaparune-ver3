package com.green.attaparunever2.admin.system.model;

import com.green.attaparunever2.common.model.Paging;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSystemPostReq extends Paging {
    public GetSystemPostReq(Integer page, Integer size) {
        super(page, size);
    }
}
