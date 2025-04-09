package com.green.attaparunever2.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignInAdminReq {
    @Schema(description = "아이디", example = "qwe")
    @JsonProperty("id")
    private String aid;
    @Schema(description = "비밀번호", example = "qwe")
    @JsonProperty("pw")
    private String apw;
}
