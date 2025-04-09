package com.green.attaparunever2.admin.model;

import com.green.attaparunever2.common.model.Paging;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GetCompanyAndRestaurantEnrollmentReq extends Paging {
    public GetCompanyAndRestaurantEnrollmentReq(Integer page, Integer size) {
        super(page, size);
    }
}
