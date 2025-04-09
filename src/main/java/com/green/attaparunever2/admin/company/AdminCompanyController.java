package com.green.attaparunever2.admin.company;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.attaparunever2.admin.AdminService;
import com.green.attaparunever2.admin.company.model.*;
import com.green.attaparunever2.admin.model.InsCompanyEnrollmentReq;
import com.green.attaparunever2.admin.model.SelCompanyEnrollmentRes;
import com.green.attaparunever2.admin.model.getCompanyPaymentRes;
import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.common.util.PaymentUtils;
import com.green.attaparunever2.company.CompanyService;
import com.green.attaparunever2.company.model.*;
import com.green.attaparunever2.entity.PaymentInfoTmp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.json.simple.JSONObject;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("admin/company")
@Tag(name = "관리자 회사", description = "관리자 회사 관련 API")
public class AdminCompanyController {
    private final CompanyService companyService;
    private final AdminService adminService;
    private final AdminCompanyService adminCompanyService;

    @PatchMapping("v3")
    @Operation(summary = "회사 정보 수정")
    public ResultResponse<Integer> patchCompany(@RequestBody UpdCompanyReq req) {
        int result = companyService.patchCompany(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("회사 정보 수정 완료")
                .resultData(result)
                .build();
    }

    @GetMapping("v3/employee")
    @Operation(summary = "사원 정보 조회")
    public ResultResponse<AdminCompanyEmployeeGetRes> getEmployee(@ParameterObject GetEmployeeReq req) {
        AdminCompanyEmployeeGetRes res = companyService.getEmployee(req);

        return ResultResponse.<AdminCompanyEmployeeGetRes>builder()
                .statusCode("200")
                .resultMsg("사원 정보 조회 성공")
                .resultData(res)
                .build();
    }

    @PatchMapping("v3/employee")
    @Operation(summary = "사원 상태 변경")
    public ResultResponse<Integer> patchEmployee(@RequestBody UpdEmployeeReq req) {
        int result = companyService.patchEmployee(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("사원 상태 변경 성공")
                .resultData(result)
                .build();
    }

    @PostMapping("v3/employee")
    @Operation(summary = "사원 계정 생성")
    public ResultResponse<Integer> postEmployee(@RequestBody SignUpEmployeeReq req) {
        int result = companyService.postEmployee(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("사원 계정 생성 성공")
                .resultData(result)
                .build();
    }

    @PatchMapping("v3/employee/point/collect")
    @Operation(summary = "사원 포인트 회수")
    public ResultResponse<Integer> patchEmployeePointCollect(@RequestBody UpdEmployeePointCollectReq req) {
        int result = companyService.patchEmployeePointCollect(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("사원 포인트 회수 성공")
                .resultData(result)
                .build();
    }

    @PostMapping("v3/enrollment")
    @Operation(summary = "회사 제휴 신청서 등록")
    public ResultResponse<Integer> postCompanyEnrollment(@RequestBody InsCompanyEnrollmentReq req){
        int result = adminService.postCompanyEnrollment(req);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("회사 제휴 신청서 등록 완료")
                .resultData(result)
                .build();
    }

    /*@GetMapping("v3/payment")
    @Operation(summary = "회사 포인트 판매 내역 조회")
    public ResultResponse<List<getCompanyPaymentRes>> getCompanyPayment(){
        List<getCompanyPaymentRes> res = adminService.getCompanyPayment();

        return ResultResponse.<List<getCompanyPaymentRes>>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("회사 포인트 판매 내역조회")
                .resultData(res)
                .build();
    }*/

    @PostMapping("v3/refund")
    @Operation(summary = "환불 요청")
    public ResultResponse<Integer> postRefund(@RequestBody InsRefundReq req) {
        int result = companyService.postRefund(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("환불 요청 완료")
                .resultData(result)
                .build();
    }

    @DeleteMapping("v3/refund")
    @Operation(summary = "환불 요청 취소")
    public ResultResponse<Integer> deleteRefund(@ModelAttribute DelRefundReq req) {
        int result = companyService.delRefund(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("환불 요청이 취소 되었습니다.")
                .resultData(result)
                .build();
    }

    @PostMapping("/v3/payment/temp")
    @Operation(summary = "결재전 결재 정보 임시 저장")
    public ResultResponse<PaymentInfoTmp> postPaymentTemp(@RequestBody AdminCompanyPaymentTempPostReq req) {
        log.info("온값: {}", req);
        PaymentInfoTmp result = adminCompanyService.postPaymentTemp(req);

        return ResultResponse.<PaymentInfoTmp>builder()
                .statusCode("200")
                .resultMsg("결재 정보 임시 저장을 완료했습니다.")
                .resultData(result)
                .build();
    }

    @PostMapping("/v3/point")
    @Operation(summary = "포인트 구매")
    public ResultResponse<ResponseEntity> patchPoint(HttpServletRequest request, @RequestBody String jsonBody) throws Exception {
        ResponseEntity result = adminCompanyService.patchPoint(request, jsonBody);

        return ResultResponse.<ResponseEntity>builder()
                .statusCode(result.getStatusCode().toString())
                .resultMsg("포인트 구매를 완료했습니다.")
                .resultData(result)
                .build();
    }

    @PatchMapping("/v3/point/user")
    @Operation(summary = "사용자에게 포인트 입금")
    public ResultResponse<Integer> patchPointUser(@RequestBody AdminCompanyUserPointPatchReq req) {
        adminCompanyService.patchPointUser(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("사용자 포인트 입금이 완료되었습니다.")
                .resultData(1)
                .build();
    }

    @GetMapping("/v3/point/history")
    @Operation(summary = "회사 포인트 입출금 이력")
    public ResultResponse<List<AdminCompanyPointHistory>> patchPointUser() {
        List<AdminCompanyPointHistory> resList = adminCompanyService.getCompanyPointHistoryByAdminId();

        return ResultResponse.<List<AdminCompanyPointHistory>>builder()
                .statusCode("200")
                .resultMsg("회사 포인트 입출금 이력 조회를 완료했습니다.")
                .resultData(resList)
                .build();
    }

    @GetMapping("v3/purchaseHistory")
    @Operation(summary = "회사 포인트 구매 이력")
    public ResultResponse<CompanyPurchaseHistoryGetRes> getPurchaseHistory(SelPurchaseHistoryReq req){
        CompanyPurchaseHistoryGetRes res = adminCompanyService.getPurchaseHistory(req);

        return ResultResponse.<CompanyPurchaseHistoryGetRes>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("회사 포인트 구매 이력 조회 완료")
                .resultData(res)
                .build();
    }

    @GetMapping("v3/depositDetail")
    @Operation(summary = "포인트 입금 내역")
    public ResultResponse<CompanyDepositHistoryGetRes> getDepositDetail(SelDepositDetailReq req){
        CompanyDepositHistoryGetRes res = adminCompanyService.getDepositDetail(req);

        return ResultResponse.<CompanyDepositHistoryGetRes>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("포인트 입금 내역 조회 완료")
                .resultData(res)
                .build();
    }

    @GetMapping("v3/info")
    @Operation(summary = "회사 및 관리자 정보 조회")
    public ResultResponse<SelCompanyAndAdminInfoRes> getCompanyAndAdminInfo(SelCompanyAndAdminInfoReq req){
        SelCompanyAndAdminInfoRes res = adminCompanyService.getCompanyAndAdminInfo(req);

        return ResultResponse.<SelCompanyAndAdminInfoRes>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("회사 및 관리자 정보 조회 완료")
                .resultData(res)
                .build();
    }
}
