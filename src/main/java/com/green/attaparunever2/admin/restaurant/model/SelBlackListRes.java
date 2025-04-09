package com.green.attaparunever2.admin.restaurant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelBlackListRes {
    @Schema(description = "유저 PK")
    private long userId;
    @Schema(description = "유저 id")
    private String uid;
    @Schema(description = "닉네임")
    private String nickName;
}
