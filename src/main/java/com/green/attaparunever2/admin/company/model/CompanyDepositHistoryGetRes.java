package com.green.attaparunever2.admin.company.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CompanyDepositHistoryGetRes {
    private int totalPageCount;
    private List<SelDepositDetailRes> historyList;
}
