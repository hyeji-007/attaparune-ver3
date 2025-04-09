package com.green.attaparunever2.admin.system.model;

import com.green.attaparunever2.admin.model.SelQuestionPostRes;
import com.green.attaparunever2.admin.model.SelSystemPostRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SystemQuestionGetRes {
    private int totalPageCount;
    private List<SelQuestionPostRes> postList;
}
