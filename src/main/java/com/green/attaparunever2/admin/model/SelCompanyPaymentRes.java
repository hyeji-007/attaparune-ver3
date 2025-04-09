package com.green.attaparunever2.admin.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SelCompanyPaymentRes {
    private int totalPageCount;

    private List<getCompanyPaymentRes> getCompanyPaymentResList;
}
