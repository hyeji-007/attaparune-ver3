package com.green.attaparunever2.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "비밀번호 수정 요청")
public class UserUpwPatchReq {

    @JsonIgnore
    @Schema(title = "사용자 ID")
    private long userId;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*()-_=+\\\\\\\\|\\\\[{\\\\]};:'\\\",<.>/?]).{8,}$"
            , message = "비밀번호는 특수문자와 숫자를 포함한 8자 이상이어야 합니다.")
    @Schema(title = "신규 비밀번호", example = "123abc!@#", requiredMode = Schema.RequiredMode.REQUIRED)
    private String newUpw;

}
