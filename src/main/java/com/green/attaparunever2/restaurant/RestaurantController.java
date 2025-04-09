package com.green.attaparunever2.restaurant;


import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.restaurant.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Result;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("restaurant")
@Tag(name = "식당", description = "식당 관리")
public class RestaurantController {
    private final RestaurantService restaurantService;

    //------------------------------------------------------------------------------------------------------------------------------------------

    @GetMapping("/main")
    @Operation(summary = "메인 화면 식당 조회")
    public ResultResponse<List<GetRestaurantMainRes>> getRestaurantMain(@ParameterObject @ModelAttribute GetRestaurantMainReq req){
        List<GetRestaurantMainRes> res = restaurantService.getRestaurantMainV3(req);

        return ResultResponse.<List<GetRestaurantMainRes>>builder()
                .statusCode("200")
                .resultMsg("메인 화면 식당 정보 조회 완료")
                .resultData(res)
                .build();
    }

    @GetMapping("v3/main/recommend")
    @Operation(summary = "메인 화면 추천 식당 조회")
    public ResultResponse<List<GetRestaurantMainLimit3Res>> getRestaurantMainLimit3(@ParameterObject @ModelAttribute GetRestaurantMainLimit3Req req){
        List<GetRestaurantMainLimit3Res> res = restaurantService.getRestaurantMainLimit3(req);

        return ResultResponse.<List<GetRestaurantMainLimit3Res>>builder()
                .statusCode("200")
                .resultMsg("메인 화면 추천 식당 정보 조회 완료")
                .resultData(res)
                .build();
    }

    @GetMapping("/around")
    @Operation(summary = "식당 찾기 탭 식당 리스트 조회")
    public ResultResponse<List<RestaurantAroundGetRes>> getRestaurantAround(@ParameterObject @ModelAttribute @Valid RestaurantAroundGetReq req){
        List<RestaurantAroundGetRes> res = restaurantService.getRestaurantAroundV3(req);

        return ResultResponse.<List<RestaurantAroundGetRes>>builder()
                .statusCode("200")
                .resultMsg("주변 식당 보기 완료")
                .resultData(res)
                .build();
    }

    @GetMapping()
    @Operation(summary = "식당 상세 정보 보기")
    public ResultResponse<RestaurantDetailGetRes> getRestaurantV3(@ParameterObject @ModelAttribute @Valid RestaurantDetailGetReq req){
        RestaurantDetailGetRes res = restaurantService.getRestaurantDetailV3(req);

        return ResultResponse.<RestaurantDetailGetRes>builder()
                .statusCode("200")
                .resultMsg("식당 보기 성공")
                .resultData(res)
                .build();
    }

    @GetMapping("v3/review")
    @Operation(summary = "식당 리뷰 및 별점별 리뷰 개수 조회")
    public ResultResponse<ReviewResponseDto> getReviewResponse(@ParameterObject @ModelAttribute GetRestaurantReviewReq p) {
        ReviewResponseDto res = restaurantService.getRestaurantReview(p);

        return ResultResponse.<ReviewResponseDto>builder()
                .statusCode("200")
                .resultMsg("식당 리뷰 조회가 완료되었습니다.")
                .resultData(res)
                .build();
    }

    @GetMapping("dashboard")
    @Operation(summary = "식당 매출정보 대시보드 조회")
    public ResultResponse<SelRestaurantDashBoardRes> getRestaurantDashboard(@ParameterObject @ModelAttribute SelRestaurantDashboardReq p){
        SelRestaurantDashBoardRes res = restaurantService.getRestaurantDashboard(p);

        return ResultResponse.<SelRestaurantDashBoardRes>builder()
                .statusCode("200")
                .resultMsg("식당 매출 정보 대시보드 정보 조회 완료.")
                .resultData(res)
                .build();
    }

}