package com.green.attaparunever2.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignInAdminRes {
    @Schema(description = "관리자 PK")
    private long adminId;

    @Schema(description = "관리자 구분")
    private String code;

    @Schema(description = "아이디")
    private String aid;

    @Schema(description = "제휴 상태")
    private int coalitionState;

    @JsonIgnore
    @Schema(description = "비밀번호")
    private String apw;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "전화번호")
    private String phone;

    @Schema(description = "식당, 회사 PK")
    private long divisionId;

    @Schema(description = "엑세스 토큰")
    private String accessToken;
}
