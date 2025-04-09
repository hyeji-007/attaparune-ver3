package com.green.attaparunever2.admin.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetCompanyEnrollmentRes {
    private int totalPageCount;

    private List<SelCompanyEnrollmentRes> selCompanyEnrollmentResList;
}
