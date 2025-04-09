package com.green.attaparunever2.user.user_payment_member;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.user.UserService;
import com.green.attaparunever2.user.model.CompanyUserGetReq;
import com.green.attaparunever2.user.model.CompanyUserGetRes;
import com.green.attaparunever2.user.user_payment_member.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/user-payment-member")
@Tag(name="유저 결제 및 포인트 관련", description = "유저 결제 및 포인트 관련 API")
public class UserPaymentMemberController {
    private final UserPaymentMemberService userPaymentMemberService;
    private final UserService userService;

    @GetMapping("point")
    @Operation(summary = "남은 포인트 조회")
    public ResultResponse<UserGetPointRes> getPoint(@ModelAttribute UserGetPointReq p) {
        UserGetPointRes result = userPaymentMemberService.getPoint(p.getUserId());

        return ResultResponse.<UserGetPointRes>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("포인트 조회 완료.")
                .resultData(result)
                .build();
    }

    @PatchMapping("point")
    @Operation(summary = "v3/승인 요청 금액 수정")
    public ResultResponse<Integer> updPaymentAmount(UserPaymentAmountPatchReq p) {
        int result = userPaymentMemberService.updPaymentAmount(p);
        if (result == 0) {
            return ResultResponse.<Integer>builder()
                    .statusCode("400")
                    .resultMsg("금액 수정 실패")
                    .resultData(0)
                    .build();
        }

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("금액 수정 완료")
                .resultData(1)
                .build();
    }

    @DeleteMapping
    @Operation(summary = "결제 인원 삭제")
    public ResultResponse<Integer> deletePaymentMember(UserPaymentMemberDelReq p) {
        int result = userPaymentMemberService.deletePaymentMember(p);
        if (result == 0) {
            return ResultResponse.<Integer>builder()
                    .statusCode("400")
                    .resultMsg("결제 인원 삭제 실패")
                    .resultData(0)
                    .build();
        }

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("결제 인원 삭제 완료")
                .resultData(1)
                .build();
    }

    @GetMapping("my")
    @Operation(summary = "내게 온 결제 승인 요청 정보를 조회한다.")
    public ResultResponse<UserGetPaymentInfoRes> getPaymentInfo(@ParameterObject UserGetPaymentInfoReq p) {
        UserGetPaymentInfoRes result = userPaymentMemberService.getPaymentInfo(p);

        return ResultResponse.<UserGetPaymentInfoRes>builder()
                .statusCode("200")
                .resultMsg("내게 온 결제 승인요청 정보조회 완료.")
                .resultData(result)
                .build();
    }

    @PatchMapping
    @Operation(summary = "내게 온 결제 승인 요청 처리", description = "승인 및 거부 처리")
    public ResultResponse<Integer> patchPaymentMember(@RequestBody UserPatchPaymentMemberReq p) {
        int result = userPaymentMemberService.patchPaymentMember(p);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("내게 온 결제 승인 요청 수정 완료")
                .resultData(result)
                .build();
    }

    @PostMapping
    @Operation(summary = "v3/결제 승인 요청")
    public ResultResponse<Integer> postPaymentMember(@RequestBody UserPostPaymentMemberReq p) {
        int result = userPaymentMemberService.postPaymentMember(p);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("승인 요청 보내기 성공")
                .resultData(result)
                .build();
    }

    @GetMapping
    @Operation(summary = "유저 결제 멤버 확인")
    public ResultResponse<List<SelUserPaymentMemberRes>> getUserPaymentMember(long orderId) {
        List<SelUserPaymentMemberRes> resList = userPaymentMemberService.getUserPaymentMember(orderId);

        return ResultResponse.<List<SelUserPaymentMemberRes>>builder()
                .statusCode("200")
                .resultMsg("유저 결제 멤버 확인")
                .resultData(resList)
                .build();
    }

    @GetMapping("approval-status")
    @Operation(summary = "승인상태확인")
    public ResultResponse<List<SelUserOrderApprovalRes>> getUserOrderApprovalAccess(long orderId) {
        List<SelUserOrderApprovalRes> list = userPaymentMemberService.getUserOrderApprovalAccess(orderId);

        return ResultResponse.<List<SelUserOrderApprovalRes>>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("승인상태확인")
                .resultData(list)
                .build();
    }

    @GetMapping("/searchPeople")
    @Operation(summary = "함께 결재할 인원 조회")
    public ResultResponse<List<CompanyUserGetRes>> getCompanyUser(@ParameterObject @ModelAttribute @Valid CompanyUserGetReq req) {

        List<CompanyUserGetRes> resList = userService.getCompanyUser(req);

        return ResultResponse.<List<CompanyUserGetRes>>builder()
                .statusCode("200")
                .resultMsg("결제 인원 검색 완료")
                .resultData(resList)
                .build();
    }

    @PostMapping("insTicket")
    @Operation(summary = "티켓생성")
    public ResultResponse<Long> postTicket(@RequestBody PostTicketReq p){
        long result = userPaymentMemberService.postTicket(p);

        return ResultResponse.<Long>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("티켓생성완료")
                .resultData(result)
                .build();
    }

}
