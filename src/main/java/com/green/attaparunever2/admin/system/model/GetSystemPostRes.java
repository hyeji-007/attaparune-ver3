package com.green.attaparunever2.admin.system.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetSystemPostRes {
    private int totalPageCount;

    private List<SelSystemPostRes> selSystemPostResList;
}
