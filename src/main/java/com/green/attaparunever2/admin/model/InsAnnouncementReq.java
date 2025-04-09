package com.green.attaparunever2.admin.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsAnnouncementReq {
    @JsonIgnore
    private String postCode;

    @NotEmpty(message = "제목을 입력해주세요")
    @Schema(description = "제목", example = "안녕하세요", requiredMode = Schema.RequiredMode.REQUIRED)
    private String inquiryTitle;

    @NotEmpty(message = "내용을 입력해주세요")
    @Schema(description = "내용", example = "반갑습니다", requiredMode = Schema.RequiredMode.REQUIRED)
    private String inquiryDetail;

    @JsonIgnore
    @Schema(description = "사진")
    private String pic;

    @NotEmpty(message = "시스템 PK를 입력해주세요")
    @Schema(description = "시스템 관리자 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @JsonIgnore
    @Schema(description = "권한코드", example = "00104", requiredMode = Schema.RequiredMode.REQUIRED)
    private String roleCode;

}
