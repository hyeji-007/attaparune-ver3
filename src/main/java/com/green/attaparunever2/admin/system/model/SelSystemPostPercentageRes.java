package com.green.attaparunever2.admin.system.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelSystemPostPercentageRes {
    @Schema(description = "문의사항 수")
    private int countForInquiry;

    @Schema(description = "불편사항 수")
    private int countForInconvenience;

    @Schema(description = "총 게시글 수")
    private int totalCount;
}
