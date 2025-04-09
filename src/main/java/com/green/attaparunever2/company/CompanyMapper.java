package com.green.attaparunever2.company;

import com.green.attaparunever2.company.model.GetEmployeeReq;
import com.green.attaparunever2.company.model.GetEmployeeRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {
    List<GetEmployeeRes> selEmployee(GetEmployeeReq req);
    int selEmployeeCount(GetEmployeeReq req);
}
