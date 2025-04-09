package com.green.attaparunever2.admin.restaurant;

import com.green.attaparunever2.admin.AdminService;
import com.green.attaparunever2.admin.model.InsRestaurantEnrollmentReq;
import com.green.attaparunever2.admin.model.SelRestaurantEnrollmentRes;
import com.green.attaparunever2.admin.restaurant.model.*;
import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.entity.RestaurantMenu;
import com.green.attaparunever2.order.OrderService;
import com.green.attaparunever2.order.model.OrderAccessPatchReq;
import com.green.attaparunever2.order.model.OrderListSelReq;
import com.green.attaparunever2.order.model.RestaurantOrderDto;
import com.green.attaparunever2.restaurant.RestaurantService;
import com.green.attaparunever2.restaurant.model.*;
import com.green.attaparunever2.restaurant.restaurant_menu.RestaurantMenuService;
import com.green.attaparunever2.restaurant.restaurant_menu.model.DelMenuReq;
import com.green.attaparunever2.restaurant.restaurant_menu.model.PostMenuReq;
import com.green.attaparunever2.restaurant.restaurant_menu.model.UpdCategoryReq;
import com.green.attaparunever2.restaurant.restaurant_menu.model.UpdMenuReq;
import com.green.attaparunever2.restaurant.restaurant_pic.RestaurantPicService;
import com.green.attaparunever2.restaurant.restaurant_pic.model.UpdRestaurantMenuPicReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/restaurant")
@Tag(name = "관리자 식당", description = "관리자 식당 관련 API")
public class AdminRestaurantController {
    private final RestaurantService restaurantService;
    private final RestaurantMenuService restaurantMenuService;
    private final OrderService orderService;
    private final AdminService adminService;
    private final RestaurantPicService restaurantPicService;
    private final AdminRestaurantService adminRestaurantService;

    @PatchMapping
    @Operation(summary = "v3/식당 정보 수정")
    public ResultResponse<Integer> updateRestaurant(@RequestBody UpdRestaurantReq req){
        int result = restaurantService.patchRestaurant(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("식당 정보 수정 완료")
                .resultData(result)
                .build();
    }


    @PostMapping("v3/menu")
    @Operation(summary = "메뉴 등록")
    public ResultResponse<RestaurantMenu> postMenu(@RequestPart PostMenuReq p,
                                                   @RequestPart(required = false) MultipartFile pic) {

        RestaurantMenu savedMenu = adminRestaurantService.postMenu(pic, p);

        return ResultResponse.<RestaurantMenu>builder()
                .statusCode("200")
                .resultMsg("메뉴 등록 성공")
                .resultData(savedMenu)
                .build();
    }

    @PatchMapping("/menu")
    @Operation(summary = "메뉴 정보 수정")
    public ResultResponse<Integer> updateMenu(@RequestBody UpdMenuReq p){
        int result = restaurantMenuService.updRestaurantMenu(p);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("메뉴 수정 완료")
                .resultData(result)
                .build();
    }


    @DeleteMapping("v3/menu")
    @Operation(summary = "메뉴 정보 삭제")
    public ResultResponse<String> deleteMenu(@ParameterObject @ModelAttribute DelMenuReq p) {
        adminRestaurantService.deleteMenu(p);

        return ResultResponse.<String>builder()
                .statusCode("200")
                .resultMsg("메뉴 삭제 완료")
                .resultData("메뉴 삭제가 완료되었습니다.")
                .build();
    }

    @PatchMapping("v3/menu/category")
    @Operation(summary = "메뉴 카테고리 수정")
    public ResultResponse<Integer> updateCategory(@RequestBody UpdCategoryReq p) {
        int result = restaurantMenuService.updCategory(p);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("메뉴 카테고리 수정 완료")
                .resultData(result)
                .build();
    }

    @GetMapping("/order")
    @Operation(summary = "식당 주문 조회")
    public ResultResponse<List<RestaurantOrderDto>> getCompleteOrder(@ParameterObject @ModelAttribute OrderListSelReq p) {
        List<RestaurantOrderDto> res = orderService.getCompleteOrder(p);
        return ResultResponse.<List<RestaurantOrderDto>>builder()
                .statusCode("200")
                .resultMsg("주문 조회 완료")
                .resultData(res)
                .build();
    }

    @GetMapping("/order/list")
    @Operation(summary = "식당 매출정보 리스트 조회")
    public ResultResponse<List<SelRestaurantOrderRes>> getRestaurantPoint(@ParameterObject @ModelAttribute SelRestaurantOrderReq p){
        List<SelRestaurantOrderRes> res = restaurantService.getRestaurantPoint(p);

        return ResultResponse.<List<SelRestaurantOrderRes>>builder()
                .statusCode("200")
                .resultMsg("식당 매출 정보 대시보드 정보 조회 완료.")
                .resultData(res)
                .build();
    }

    @PutMapping("/order/reservation/status")
    @Operation(summary = "주문 상태 변경", description = "0:미승인, 1:승인, 2:거부, 3:취소")
    public ResultResponse<Long> updOrderAccess(@Valid @RequestBody OrderAccessPatchReq p
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultResponse.<Long>builder()
                    .statusCode("400")
                    .resultMsg("주문 상태 변경 실패")
                    .resultData(0L)
                    .build();
        }

        orderService.updOrderAccess(p);
        return ResultResponse.<Long>builder()
                .statusCode("200")
                .resultMsg("주문 상태 변경 완료")
                .resultData(1L)
                .build();
    }

    @GetMapping("order/reservation")
    @Operation(summary = "식당 주문 조회(예약)")
    public ResultResponse<List<RestaurantOrderDto>> getReservationOrder(@ParameterObject @ModelAttribute OrderListSelReq p) {
        List<RestaurantOrderDto> res = orderService.getReservationOrder(p);
        return ResultResponse.<List<RestaurantOrderDto>>builder()
                .statusCode("200")
                .resultMsg("주문 조회 완료")
                .resultData(res)
                .build();
    }

    @PostMapping("v3/enrollment")
    @Operation(summary = "식당 입점 신청서 작성")
    public ResultResponse<Integer> postRestaurantEnrollment(@RequestBody InsRestaurantEnrollmentReq req){
        int result = adminService.postRestaurantEnrollment(req);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("식당 입점 신청서 등록 완료")
                .resultData(result)
                .build();
    }

    @PatchMapping("/v3/pic/menu")
    @Operation(summary = "식당 메뉴 사진 수정")
    public ResultResponse<String> updRestaurantMenuPic(@RequestPart MultipartFile pic, @RequestPart UpdRestaurantMenuPicReq p) {
        String result = restaurantPicService.updRestaurantMenuPic(pic, p);

        return ResultResponse.<String>builder()
                .statusCode("200")
                .resultMsg("식당 메뉴 사진 수정 성공")
                .resultData(result)
                .build();
    }

    @PostMapping("v3/pic")
    @Operation(summary = "식당 사진 등록")
    public ResultResponse<InsRestaurantRes> postRestaurantPic(@RequestPart List<MultipartFile> picName, @RequestParam long restaurantId) {
        InsRestaurantRes res = restaurantPicService.postRestaurantPic(picName, restaurantId);

        return ResultResponse.<InsRestaurantRes>builder()
                .statusCode("200")
                .resultMsg("식당 사진 등록 성공")
                .resultData(res)
                .build();
    }

    @DeleteMapping("/v3/pic")
    @Operation(summary = "식당 사진 삭제")
    public ResultResponse<Integer> delRestaurantPic(
            @Parameter(description = "식당 사진 ID", example = "[1, 2, 3]", required = true)
            @RequestParam List<Long> picId,
            @RequestParam long restaurantId) {
        // service 호출 및 응답 처리
        int result = restaurantPicService.delRestaurantPic(restaurantId, picId);
        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("식당 사진 삭제 성공")
                .resultData(result)
                .build();
    }

    @GetMapping("holiday")
    @Operation(summary = "휴무일 보기")
    public ResultResponse<List<SelHolidayRes>> getHoliday(@ParameterObject @ModelAttribute SelHolidayReq p){
        List<SelHolidayRes> res = restaurantService.getHoliday(p);

        return ResultResponse.<List<SelHolidayRes>>builder()
                .statusCode("200")
                .resultMsg("휴무일 보기 완료")
                .resultData(res)
                .build();
    }

    @PostMapping("v3/holiday")
    @Operation(summary = "휴무일 등록")
    public ResultResponse<Integer> postHoliday(@RequestBody InsHolidayReq p){
        int result = restaurantService.postHoliday(p);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("휴무일 등록 성공")
                .resultData(result)
                .build();
    }

    @PatchMapping("v3/holiday")
    @Operation(summary = "휴무일 변경")
    public ResultResponse<Integer> updateHoliday(@RequestBody UpdHolidayReq req){
        int result = restaurantService.patchHoliday(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("휴무일 변경 완료")
                .resultData(result)
                .build();
    }

    @PostMapping("v3/review")
    @Operation(summary = "리뷰 댓글 등록")
    public ResultResponse<Integer> postReviewComment(@RequestBody InsReviewCommentReq req) {
        int result = adminRestaurantService.postReviewComment(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("리뷰 댓글 등록 성공")
                .resultData(result)
                .build();
    }

    @PatchMapping("v3/review/del-request")
    @Operation(summary = "리뷰 삭제 요청")
    public ResultResponse<Integer> patchReviewDelRequest(@RequestBody UpdReviewDelRequestReq req) {
        int result = adminRestaurantService.patchReviewDelRequest(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("리뷰 삭제 요청 성공")
                .resultData(result)
                .build();
    }

    @PostMapping("v3/black-list")
    @Operation(summary = "블랙리스트 등록")
    public ResultResponse<Integer> postBlackList(@RequestBody InsBlackListReq req) {
        int result = adminRestaurantService.postBlackList(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("블랙리스트 등록 성공")
                .resultData(result)
                .build();
    }

    @DeleteMapping("v3/black-list")
    @Operation(summary = "블랙리스트 삭제")
    public ResultResponse<Integer> delBlackList(@ModelAttribute DelBlackListReq req) {
        int result = adminRestaurantService.delBlackList(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("블랙리스트 삭제 완료")
                .resultData(result)
                .build();
    }

    @PatchMapping("v3/password")
    @Operation(summary = "간편결제 비밀번호 수정")
    public ResultResponse<Integer> patchPaymentPassword(@Valid @RequestBody UpdPaymentPasswordReq req) {
        int result = adminRestaurantService.patchPaymentPassword(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("간편결제 비밀번호 수정 완료")
                .resultData(result)
                .build();
    }

   /* @GetMapping("dashboard")
    @Operation(summary = "식당 매출정보 대시보드 조회")
    public ResultResponse<SelRestaurantDashBoardRes> getRestaurantDashboard(@ParameterObject @ModelAttribute SelRestaurantDashboardReq p){
        SelRestaurantDashBoardRes res = restaurantService.getRestaurantDashboard(p);

        return ResultResponse.<SelRestaurantDashBoardRes>builder()
                .statusCode("200")
                .resultMsg("식당 매출 정보 대시보드 정보 조회 완료.")
                .resultData(res)
                .build();
    }*/

    @DeleteMapping("reviewComment")
    @Operation(summary = "리뷰 댓글 삭제")
    public ResultResponse<Integer> delReviewComment(@ModelAttribute DelReviewCommentReq req) {
        int result = adminRestaurantService.delReviewComment(req);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("리뷰 댓글 삭제 완료")
                .resultData(result)
                .build();
    }

    @GetMapping("v3/black-list")
    @Operation(summary = "블랙리스트 조회")
    public ResultResponse<List<SelBlackListRes>> getBlackList(@ModelAttribute SelBlackListReq req) {
        List<SelBlackListRes> res = adminRestaurantService.getBlackList(req);

        return ResultResponse.<List<SelBlackListRes>>builder()
                .statusCode("200")
                .resultMsg("블랙리스트 조회 완료")
                .resultData(res)
                .build();
    }
}
