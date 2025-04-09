package com.green.attaparunever2.admin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUpwPatchRes {
    @Schema(title = "관리자 ID")
    private long adminId;
}
