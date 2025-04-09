package com.green.attaparunever2.admin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelSystemPostRes {
    @Schema(description = "게시글 코드")
    private String postCode;
    @Schema(description = "게시글 PK")
    private long inquiryId;
    @Schema(description = "게시글 제목")
    private String inquiryTitle;
    @Schema(description = "권한 코드")
    private String roleCode;
    @Schema(description = "작성일")
    private String createdAt;
}
