package com.green.attaparunever2.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignAdminReq {
    @Schema(description = "아이디", example = "qwe")
    @JsonProperty("id")
    private String aid;

    @Schema(description = "비밀번호", example = "qwe")
    @JsonProperty("pw")
    private String apw;

    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Schema(description = "이메일", example = "test@naver.com")
    private String email;

    @Schema(description = "활성화, 비활성화", example = "0")
    private int coalitionState;

    @Schema(description = "전화번호", example = "01012341234")
    private String phone;

    @JsonIgnore
    private long adminId;
}
