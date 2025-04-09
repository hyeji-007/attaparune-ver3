package com.green.attaparunever2.admin.system;

import com.green.attaparunever2.admin.AdminService;
import com.green.attaparunever2.admin.model.*;
import com.green.attaparunever2.admin.system.model.*;
import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.common.repository.CodeRepository;
import com.green.attaparunever2.entity.Code;
import com.green.attaparunever2.user.model.GetReviewReq;
import com.green.attaparunever2.user.model.GetReviewRequestDto;
import com.green.attaparunever2.user.model.GetReviewRequestReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("admin/system")
@Tag(name="관리자 시스템", description = "관리자 시스템 관련 API")
public class AdminSystemController {
    private final AdminService adminService;
    private final AdminSystemService adminSystemService;

    /*@PostMapping("v3/announcement")
    @Operation(summary = "공지사항 등록하기")
    public ResultResponse<Integer> postSystemPost(@RequestPart(required = false) MultipartFile pic
            , @RequestPart InsAnnouncementReq req){
        int result = adminService.postAnnouncement(pic, req);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("공지사항 등록완료")
                .resultData(result)
                .build();
    }*/

    @GetMapping("v3/Refund")
    @Operation(summary = "환불 내역 조회")
    public ResultResponse<GetRefundRes> getRefund(@ParameterObject GetRefundReq req) {
        GetRefundRes res = adminService.getRefund(req);

        return ResultResponse.<GetRefundRes>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("환불 내역 조회 완료")
                .resultData(res)
                .build();
    }

    @PatchMapping("v3/coalition")
    @Operation(summary = "제휴 상태 관리")
    public ResultResponse<Integer> patchCoalition(@RequestBody UpdCoalitionReq req) {
        int result = adminSystemService.patchCoalition(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("제휴 상태 변경 성공")
                .resultData(result)
                .build();
    }

    @PatchMapping("v3/refund")
    @Operation(summary = "환불 요청 처리")
    public ResultResponse<Integer> patchRefund(@RequestBody UpdRefundReq req) {
        int result = adminSystemService.patchRefund(req);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("환불 요청 처리 완료")
                .resultData(result)
                .build();
    }

    @PatchMapping("v3/enrollmentState")
    @Operation(summary = "입점신청서 승인 및 거절")
    public ResultResponse<Integer> patchEnrollmentState(UpdAdmin req){
        int result = adminSystemService.patchEnrollmentState(req);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("입점신청서 상태 변경 완료")
                .resultData(result)
                .build();
    }

    @PostMapping("v3/systemPostComment")
    @Operation(summary = "시스템 문의 답변 등록")
    public ResultResponse<Integer> postSystemPostComment(InsSystemPostCommentReq req){
        int result = adminSystemService.postSystemPostComment(req);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("시스템 문의 답변 등록 완료")
                .resultData(result)
                .build();
    }
    
    @GetMapping("v3/systemPostComment")
    @Operation(summary = "시스템 문의 답변 조회")
    public ResultResponse<List<SelSystemPostCommentRes>> getSystemPostComment(SelSystemPostCommentReq req){
        List<SelSystemPostCommentRes> res = adminSystemService.getSystemPostComment(req);
        
        return ResultResponse.<List<SelSystemPostCommentRes>>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("시스템 문의 답변 조회 완료")
                .resultData(res)
                .build();
    }

    @PatchMapping("v3/systemPostComment")
    @Operation(summary = "시스템 문의 답변 수정")
    public ResultResponse<Integer> patchSystemPostComment(UpdSystemPostCommentReq req){
        int result = adminSystemService.patchSystemPostComment(req);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("시스템 문의 답변 수정 완료")
                .resultData(result)
                .build();
    }

    @DeleteMapping("v3/systemPost")
    @Operation(summary = "시스템 문의 답변 삭제")
    public ResultResponse<Integer> delSystemPostComment(DelSystemPostCommentReq req){
        int result = adminSystemService.delSystemPostComment(req);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("시스템 문의답변 삭제 완료")
                .resultData(result)
                .build();
    }

    @PostMapping("v3/settlement-day")
    @Operation(summary = "정산일자 변경")
    public ResultResponse<Integer> postSettlementDay(@RequestBody SettlementDayPostReq req) {
       int result = adminSystemService.postSettlementDay(req);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("스케줄이 변경되었습니다.")
                .resultData(result)
                .build();
    }

    @PostMapping("v3/settlement-list")
    @Operation(summary = "입금 처리")
    public ResultResponse<Integer> postSettlementList(@RequestBody InsSettlementListReq req) {
        int result = adminSystemService.postSettlementList(req);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("입금 처리 완료")
                .resultData(result)
                .build();

    }

    @GetMapping("v3/settlement-list")
    @Operation(summary = "이번 주 정산 내역")
    public ResultResponse<List<SelSettlementDetailRes>> getSettlementList(){
        List<SelSettlementDetailRes> resList = adminSystemService.getSettlementList();

        return ResultResponse.<List<SelSettlementDetailRes>>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("정산 내역 조회 완료")
                .resultData(resList)
                .build();
    }

    @GetMapping("v3/payment")
    @Operation(summary = "회사 포인트 판매 내역 조회")
    public ResultResponse<SelCompanyPaymentRes> getCompanyPayment(@ParameterObject SelCompanyPaymentReq req){
        SelCompanyPaymentRes res = adminService.getCompanyPayment(req);

        return ResultResponse.<SelCompanyPaymentRes>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("회사 포인트 판매 내역조회")
                .resultData(res)
                .build();
    }

    @GetMapping("v3/restaurant/enrollment")
    @Operation(summary = "식당 입점신청서 보기")
    public ResultResponse<GetRestaurantEnrollmentRes> getRestaurantEnrollment(@ParameterObject GetCompanyAndRestaurantEnrollmentReq req){
        GetRestaurantEnrollmentRes res = adminService.getRestaurantEnrollment(req);

        return ResultResponse.<GetRestaurantEnrollmentRes>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("식당 입점신청서 보기 완료")
                .resultData(res)
                .build();
    }

    @GetMapping("v3/company/enrollment")
    @Operation(summary = "회사 제휴신청서 보기")
    public ResultResponse<GetCompanyEnrollmentRes> getCompanyEnrollment(@ParameterObject GetCompanyAndRestaurantEnrollmentReq req){
        GetCompanyEnrollmentRes res = adminService.getCompanyEnrollment(req);

        return ResultResponse.<GetCompanyEnrollmentRes>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("회사 제휴신청서 보기 완료")
                .resultData(res)
                .build();
    }

    @GetMapping("v3/review")
    @Operation(summary = "리뷰 삭제 요청 리스트 보기")
    public ResultResponse<List<GetReviewRequestDto>> getReviewRequestList(@ParameterObject @ModelAttribute @Valid GetReviewRequestReq p) {
        List<GetReviewRequestDto> res = adminSystemService.getReviewRequestList(p);

        return ResultResponse.<List<GetReviewRequestDto>>builder()
                .statusCode("200")
                .resultMsg("리뷰 삭제 요청 리스트 보기 완료")
                .resultData(res)
                .build();
    }

    @PatchMapping("v3/review")
    @Operation(summary = "리뷰 삭제 요청 응답")
    public ResultResponse<Integer> patchReviewRequest(ReviewRequestDto p) {
        int result = adminSystemService.patchReviewRequest(p);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("리뷰 삭제 요청 응답 완료")
                .resultData(result)
                .build();
    }


    @DeleteMapping("v3/review")
    @Operation(summary = "리뷰 삭제")
    public ResultResponse<Integer> deleteReviewRequest(Long orderId) {
        int result = adminSystemService.deleteReviewRequest(orderId);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("리뷰 삭제 완료")
                .resultData(result)
                .build();
    }

    @GetMapping("v3/systemPost-management")
    @Operation(summary = "시스템 문의 관리")
    public ResultResponse<GetSystemPostRes> getSystemPostManagement(@ParameterObject GetSystemPostReq req){
        GetSystemPostRes res = adminSystemService.getSystemPost(req);

        return ResultResponse.<GetSystemPostRes>builder()
                .statusCode("200")
                .resultMsg("시스템 문의관리 조회 완료")
                .resultData(res)
                .build();
    }

    @GetMapping("v3/systemPost-count")
    @Operation(summary = "불만 접수 현황")
    public ResultResponse<SelSystemPostPercentageRes> getSystemPostPercentage(){
        SelSystemPostPercentageRes res = adminSystemService.getSystemPostPercentage();

        return ResultResponse.<SelSystemPostPercentageRes>builder()
                .statusCode("200")
                .resultMsg("불만 접수 현황 조회 완료")
                .resultData(res)
                .build();
    }
}
