package com.green.attaparunever2.admin.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelOneSystemPostRes {
    @Schema(description = "게시글 PK")
    private long inquiryId;
    @Schema(description = "관리자 or 사용자 PK")
    private long id;
    @Schema(description = "게시글 제목")
    private String inquiryTitle;
    @Schema(description = "게시글 내용")
    private String inquiryDetail;
    @Schema(description = "게시글 사진")
    private String pic;
    @Schema(description = "게시글 코드")
    private String postCode;
    @Schema(description = "권한 코드")
    private String roleCode;
    @Schema(description = "생성일")
    private String createdAt;
}
