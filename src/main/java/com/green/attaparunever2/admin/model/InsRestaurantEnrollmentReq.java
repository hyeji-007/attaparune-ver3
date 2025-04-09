package com.green.attaparunever2.admin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsRestaurantEnrollmentReq {
    @NotEmpty(message = "카테고리 PK를 입력해주세요")
    @Schema(description = "카테고리 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long categoryId;

    @NotEmpty(message = "사업자 번호를 입력해주세요")
    @Schema(description = "사업자번호", example = "12345678", requiredMode = Schema.RequiredMode.REQUIRED)
    private String businessNumber;

    @NotEmpty(message = "식당이름을 입력해주세요")
    @Schema(description = "식당 이름", example = "국밥집", requiredMode = Schema.RequiredMode.REQUIRED)
    private String restaurantName;

    @NotEmpty(message = "이메일을 입력해주세요")
    @Schema(description = "이메일", example = "test@naver.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,10}$", message = "유효하지 않은 형식의 이메일입니다.")
    private String email;

    @NotEmpty(message = "식당 번호를 입력해주세요")
    @Schema(description = "식당 번호", example = "01012341234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String restaurantNumber;

    @NotEmpty(message = "식당 주소를 입력해주세요")
    @Schema(description = "식당 주소", example = "대구광역시 중구 1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String restaurantAddress;

    @NotEmpty(message = "위도를 입력해주세요")
    @Schema(description = "위도", example = "13", requiredMode = Schema.RequiredMode.REQUIRED)
    private double lat;

    @NotEmpty(message = "경도를 입력해주세요")
    @Schema(description = "경도", example = "14", requiredMode = Schema.RequiredMode.REQUIRED)
    private double lng;

    @Schema(description = "식당 설명", example = "국밥집입니다", requiredMode = Schema.RequiredMode.REQUIRED)
    private String restaurantDescription;

    @NotEmpty(message = "최대 수용수를 입력해주세요")
    @Schema(description = "최대 수용수", example = "40", requiredMode = Schema.RequiredMode.REQUIRED)
    private int maxCapacity;

    @Schema(description = "식당 운영시간", example = "12시 ~ 22시", requiredMode = Schema.RequiredMode.REQUIRED)
    private String operatingHours;
}
