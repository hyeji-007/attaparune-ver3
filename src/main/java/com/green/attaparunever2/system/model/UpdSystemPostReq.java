package com.green.attaparunever2.system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdSystemPostReq {
    @Schema(description = "게시글 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long inquiryId;

    @Schema(description = "관리자 or 사용자 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long id;

    @Schema(description = "게시글 제목", example = "시스템 개선 요구")
    private String inquiryTitle;

    @Schema(description = "게시글 내용", example = "string")
    private String inquiryDetail;

    @JsonIgnore
    @Schema(description = "게시글 사진")
    private String pic;
}
