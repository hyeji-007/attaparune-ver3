package com.green.attaparunever2.restaurant.model;

import com.green.attaparunever2.restaurant.restaurant_menu.model.MenuSelCateList;
import com.green.attaparunever2.restaurant.restaurant_pic.model.RestaurantPicSel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantDetailGetRes {
    @Schema(description = "카테고리 PK")
    private long categoryId;
    @Schema(description = "식당 PK")
    private long restaurantId;
    @Schema(description = "식당 이름")
    private String restaurantName;
    @Schema(description = "식당 주소")
    private String restaurantAddress;
    @Schema(description = "식당 번호")
    private String restaurantNumber;
    @Schema(description = "식당 사업자번호")
    private String businessNumber;
    @Schema(description = "식당 영업시간")
    private String operatingHours;
    @Schema(description = "식당 설명")
    private String restaurantDescription;
    @Schema(description = "식당 상태")
    private int status;
    @Schema(description = "식당 인원 수용량")
    private int maxCapacity;
    @Schema(description = "생성 시간")
    private String createdAt;
    @Schema(description = "식당 위도")
    private double lat;
    @Schema(description = "식당 경도")
    private double lng;
    @Schema(description = "식당 사진 리스트")
    private RestaurantPicSel restaurantPics;
    @Schema(description = "메뉴 카테고리 리스트")
    private List<MenuSelCateList> menuCateList;
    @Schema(description = "평균 별점")
    private double ratingAvg;
    @Schema(description = "리뷰 개수")
    private double reviewCnt;
}
