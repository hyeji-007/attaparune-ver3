package com.green.attaparunever2.admin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelRestaurantEnrollmentRes {
    @Schema(description = "식당 PK")
    private long restaurantId;
    @Schema(description = "식당 연락처")
    private String restaurantNumber;
    @Schema(description = "제휴상태")
    private int coalitionState;
    @Schema(description = "카테고리 PK")
    private long categoryId;
    @Schema(description = "카테고리 명")
    private String categoryName;
    @Schema(description = "식당 이름")
    private String restaurantName;
    @Schema(description = "식당 주소")
    private String restaurantAddress;
    @Schema(description = "식당 사업자 번호")
    private String businessNumber;
    @Schema(description = "식당 영업시간")
    private String OperatingHours;
    @Schema(description = "식당 설명")
    private String restaurantDescription;
    @Schema(description = "식당 상태")
    private int status;
    @Schema(description = "식당 인원수")
    private int maxCapacity;
    /*@Schema(description = "식당 위도")
    private double lat;
    @Schema(description = "식당 경도")
    private double lng;
    @Schema(description = "식당 간단 비밀번호")
    private String paymentPassword;*/
    @Schema(description = "식당 신청 일자")
    private String createdAt;
}
