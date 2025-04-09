package com.green.attaparunever2.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyUserGetRes {
    @Schema(description = "사용자 이름")
    private String name;
    @Schema(description = "사용자 아이디")
    private String uid;
    @Schema(description = "사용자 PK")
    private long userId;
}
