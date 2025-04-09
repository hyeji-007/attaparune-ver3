package com.green.attaparunever2.admin.company.dashboard.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelMySystemPostReq {
    @NotEmpty(message = "관리자 PK를 입력해주세요")
    @Schema(description = "관리자 PK", example = "1")
    private Long adminId;

}
