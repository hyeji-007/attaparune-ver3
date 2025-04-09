package com.green.attaparunever2.admin.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsCompanyEnrollmentReq {
    @NotEmpty(message = "회사 사업자 번호를 입력해주세요")
    @Schema(description = "회사 사업자 번호", example = "123123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String businessNumber;

    @NotEmpty(message = "회사 이름을 입력해주세요")
    @Schema(description = "회사이름", example = "해피회사", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotEmpty(message = "대표자 이름을 입력해주세요")
    @Schema(description = "대표자 이름", example = "홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ceoName;

    @NotEmpty(message = "회사 주소를 입력해주세요")
    @Schema(description = "회사주소", example = "대구광역시 중구 2", requiredMode = Schema.RequiredMode.REQUIRED)
    private String address;

    @NotEmpty(message = "이메일을 입력해주세요")
    @Schema(description = "이메일", example = "test@naver.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
}
