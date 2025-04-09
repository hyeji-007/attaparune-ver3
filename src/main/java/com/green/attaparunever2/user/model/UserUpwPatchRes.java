package com.green.attaparunever2.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpwPatchRes {
    @Schema(title = "사용자 ID", example = "10000001")
    private long userId;
    private int result;
    private String message;
}
