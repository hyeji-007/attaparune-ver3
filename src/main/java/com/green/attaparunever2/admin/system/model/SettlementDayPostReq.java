package com.green.attaparunever2.admin.system.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SettlementDayPostReq {
    @NotEmpty(message = "요일을 입력해 주세요.")
    @Size(max = 5, message = "코드는 5자리 이하 입니다.")
    @Schema(description = "요일 코드값", example = "00401", requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;
}
