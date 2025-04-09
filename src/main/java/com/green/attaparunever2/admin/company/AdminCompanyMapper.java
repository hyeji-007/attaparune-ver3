package com.green.attaparunever2.admin.company;

import com.green.attaparunever2.admin.company.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminCompanyMapper {
    List<AdminCompanyPointHistory> selCompanyPointHistoryByAdminId(long adminId);

    List<SelPurchaseHistoryRes> selPurchaseHistory(SelPurchaseHistoryReq req);
    int selPurchaseHistoryTotalCount(SelPurchaseHistoryReq req);
    List<SelDepositDetailRes> selDepositDetail(SelDepositDetailReq req);
    int selDepositTotalCount(SelDepositDetailReq req);

    SelCompanyAndAdminInfoRes selCompanyAndAdminInfo(SelCompanyAndAdminInfoReq req);
}
