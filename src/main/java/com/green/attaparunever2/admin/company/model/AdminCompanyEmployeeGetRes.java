package com.green.attaparunever2.admin.company.model;

import com.green.attaparunever2.company.model.GetEmployeeRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AdminCompanyEmployeeGetRes {
    private int totalPageCount;
    private List<GetEmployeeRes> employeeList;
}
