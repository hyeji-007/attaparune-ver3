package com.green.attaparunever2.restaurant;

import com.green.attaparunever2.restaurant.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RestaurantMapper {
    int insRestaurant(InsRestaurantReq p);
    SelRestaurantRes selRestaurant(SelRestaurantReq p);
    List<SelRestaurantAroundRes> selRestaurantAround(SelRestaurantAroundReq p);

    int insHoliday(InsHolidayReq p);
    List<SelHolidayRes> selHolidays(SelHolidayReq p);
    int updRestaurant(UpdRestaurantReq p);
    int updHoliday(UpdHolidayReq p);
    List<SelRestaurantMainRes> selRestaurantMain(SelRestaurantMainReq p);

    // 식당 일일 매출 정보 조회
    RestaurantTotalPointDto selRestaurantDayPoint(SelRestaurantDashboardReq req);
    // 식당 월별 매출 정보 조회
    RestaurantTotalPointDto selRestaurantMonthPoint(SelRestaurantDashboardReq req);
    // 식당 주간 주문 내역 조회
    List<RestaurantWeekOrderCountDto> selRestaurantWeekOrderCount(SelRestaurantWeekOrderReq req);
    // 식당 매출 정보 조회
    List<SelRestaurantOrderRes> selRestaurantPointByCreatedAt(SelRestaurantOrderReq req);

    //2차 기능 개선 코드-----------------------------------------------------------------------------
    List<GetRestaurantMainRes> selRestaurantMainV3(GetRestaurantMainReq p);
    List<GetRestaurantMainLimit3Res> selRestaurantMainV3Limit3(GetRestaurantMainLimit3Req req);
    List<RestaurantAroundGetRes> selRestaurantAroundV3(RestaurantAroundGetReq p);
    RestaurantDetailGetRes selRestaurantByRestaurantId(long restaurantId);

    // 식당 리뷰 조회
    List<ReviewDto> getReview(GetRestaurantReviewReq p);

    List<String> getReviewPic(long orderId);

    // 주문한 메뉴 이름 조회
    List<String> getMenuName(long orderId);

    // 별점별 리뷰 개수 조회
    List<RatingCountDto> getCountByRating(long restaurantId);

    // 식당명 조회
    String getRestaurantName(long restaurantId);

    // 평균 별점 조회
    double getAvgRating(long restaurantId);
}
