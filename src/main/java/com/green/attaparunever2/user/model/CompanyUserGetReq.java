package com.green.attaparunever2.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.attaparunever2.common.model.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompanyUserGetReq extends Paging {
    @JsonIgnore
    private long userId;

    @Schema(description = "검색어(이름)")
    private String searchText;

    public CompanyUserGetReq(Integer page, Integer size, String searchText) {
        super(page, size);
        this.searchText = searchText;
    }
}
