package com.green.attaparunever2.admin.company.dashboard;


import com.green.attaparunever2.admin.company.dashboard.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DashboardService {
    private final DashboardMapper dashboardMapper;

    public List<SelRecentAmountRes> getRecentAmount(SelRecentAmountReq req){
        List<SelRecentAmountRes> res = dashboardMapper.selRecentAmount(req);

        return res;
    }

    public SelTransactionRes getTransaction(SelTransactionReq req){
        SelTransactionRes res = dashboardMapper.selTransaction(req);

        return res;
    }

    public List<SelMySystemPostRes> getMySystemPost(SelMySystemPostReq req){
        List<SelMySystemPostRes> res = dashboardMapper.selMySystemPost(req);

        return res;
    }

}
