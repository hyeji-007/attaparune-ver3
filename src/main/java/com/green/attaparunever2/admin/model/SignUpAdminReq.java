package com.green.attaparunever2.admin.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignUpAdminReq {
    @NotEmpty(message = "관리자 PK를 입력해주세요")
    @Schema(description = "관리자 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long adminId;

    @NotEmpty(message = "아이디를 입력해주세요")
    @Schema(description = "아이디", example = "asd", requiredMode = Schema.RequiredMode.REQUIRED)
    private String aid;

    @NotEmpty(message = "비밀번호를 입력해주세요")
    @Schema(description = "비밀번호", example = "asd", requiredMode = Schema.RequiredMode.REQUIRED)
    private String apw;

    @NotEmpty(message = "이름을 입력해주세요")
    @Schema(description = "이름", example = "홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotEmpty(message = "전화번호를 입력해주세요")
    @Schema(description = "전화번호", example = "01012311231", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;
}
