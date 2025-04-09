package com.green.attaparunever2.admin.model;

import com.green.attaparunever2.common.model.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelQuestionPostReq extends Paging {
    @Schema(description = "검색 단어", example = "서비스")
    private String searchFilter;

    public SelQuestionPostReq(Integer page, Integer size) {
        super(page, size);
    }
}
