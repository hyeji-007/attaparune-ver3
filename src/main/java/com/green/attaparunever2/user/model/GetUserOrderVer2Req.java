package com.green.attaparunever2.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserOrderVer2Req {
    @Schema(title = "로그인한 유저 PK", requiredMode = Schema.RequiredMode.REQUIRED)
    private long signedUserId;
}
