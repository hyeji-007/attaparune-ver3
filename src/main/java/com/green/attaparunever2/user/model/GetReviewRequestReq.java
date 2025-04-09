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
public class GetReviewRequestReq extends Paging {
    @JsonIgnore
    @Schema(description = "사용자 pk")
    private Long userId;
    @Schema(description = "시작일")
    private String startDate;
    @Schema(description = "종료일")
    private String endDate;

    public GetReviewRequestReq(Integer page, Integer size) {
        super(page, size);
    }
}
