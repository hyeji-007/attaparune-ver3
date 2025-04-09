package com.green.attaparunever2.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetEmployeeRes {
    @Schema(description = "사원 PK")
    private long userId;

    @Schema(description = "사원 아이디", example = "10000001")
    private String uid;

    @Schema(description = "사원 이름", example = "홍길동")
    private String name;

    @Schema(description = "사원 연락처", example = "01012345678")
    private String phone;

    @Schema(description = "사원 이메일", example = "test@gmail.com")
    private String email;

    @Schema(description = "사원 포인트", example = "10000")
    private int point;

    @Schema(description = "사원 상태(0: 활성화, 1: 비활성화)", example = "0")
    private int activation;
}
