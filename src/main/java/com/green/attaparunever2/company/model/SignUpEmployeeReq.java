package com.green.attaparunever2.company.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpEmployeeReq {
    @NotEmpty(message = "회사 PK 입력해주세요.")
    @Schema(description = "회사 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long companyId;

    @NotEmpty(message = "관리자 PK 입력해주세요.")
    @Schema(description = "관리자 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long adminId;

    @NotEmpty(message = "사용자 이름을 입력해주세요.")
    @Schema(description = "사용자 이름", example = "홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotEmpty(message = "사원번호를 입력해주세요.")
    @Schema(description = "사원번호", example = "0001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String employeeNum;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Schema(description = "사용자 비밀번호", example = "qwer12#$", requiredMode = Schema.RequiredMode.REQUIRED)
    private String upw;

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Schema(description = "사용자 이메일", example = "test@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,10}$", message = "유효하지 않은 형식의 이메일입니다.")
    private String email;

    @NotEmpty(message = "연락처를 입력해주세요.")
    @Pattern(regexp = "^01[016789][0-9]{7,8}$", message = "유효하지 않은 형식의 전화번호입니다.")
    @Schema(description = "사용자 연락처", example = "01012345678", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;
}
