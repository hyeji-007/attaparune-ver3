package com.green.attaparunever2.user;

import com.green.attaparunever2.admin.model.AdminFindPasswordReq;
import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.entity.Review;
import com.green.attaparunever2.entity.User;
import com.green.attaparunever2.order.OrderService;
import com.green.attaparunever2.order.model.OrderPostReq;
import com.green.attaparunever2.reservation.ReservationService;
import com.green.attaparunever2.reservation.model.ReservationPostReq;
import com.green.attaparunever2.user.model.*;
import com.green.attaparunever2.user.user_payment_member.UserPaymentMemberService;
import com.green.attaparunever2.user.user_payment_member.model.PostTicketReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name="유저", description = "유저 관련 API")
public class UserController {
    private final UserService userService;
    private final ReviewService reviewService;
    private final OrderService service;
    private final UserPaymentMemberService userPaymentMemberService;
    private final ReservationService reservationService;

    /*@GetMapping
    @Operation(summary = "회원 정보 조회")
    public ResultResponse<?> getUser(@ModelAttribute UserGetReq req) {
        UserGetRes result = userService.getUser(req);

        return ResultResponse.<UserGetRes>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("회원 정보 조회 성공")
                .resultData(result)
                .build();
    }*/

    @GetMapping("auth-token")
    @Operation(summary = "인증번호 인증")
    public ResultResponse<?> authToken(@ModelAttribute AuthTokenReq req) {
        int result = userService.authToken(req);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("메일 인증에 성공했습니다.")
                .resultData(result)
                .build();
    }

    @PostMapping("sign-in")
    @Operation(summary = "로그인")
    public ResultResponse<?> signIn(@RequestBody UserSignInReq p, HttpServletResponse response) {
        UserSignInRes userSignInRes = userService.signIn(p, response);

        return ResultResponse.<UserSignInRes>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("로그인 성공")
                .resultData(userSignInRes)
                .build();
    }

    @GetMapping("find-id")
    @Operation(summary = "아이디 찾기")
    public ResultResponse<Integer> findId(@ModelAttribute UserFindIdReq p) {
        int result = userService.findId(p);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("아이디 찾기 이메일 전송 완료")
                .resultData(result)
                .build();
    }

    @GetMapping("access-token")
    @Operation(summary = "엑세스 토큰 재발행")
    public String getAccessToken(HttpServletRequest req) {
        return userService.getAccessToken(req);
    }

    @GetMapping("activeOrderCheck")
    @Operation(summary = "진행중인 주문 내역 확인")
    public ResultResponse<UserActiveOrderRes> getUserActiveOrderCheck(SelUserOrderPastCheckReq p) {
        UserActiveOrderRes res = userService.getUserActiveOrderCheck(p);

        return ResultResponse.<UserActiveOrderRes>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("진행 주문 내역 확인")
                .resultData(res)
                .build();

    }

    @GetMapping("pastOrderCheck")
    @Operation(summary = "지난 주문 내역 확인")
    public ResultResponse<List<SelUserOrderPastCheckRes>> getUserOrderCheck(){
        List<SelUserOrderPastCheckRes> res = userService.getUserOrderCheck();

        return ResultResponse.<List<SelUserOrderPastCheckRes>>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("지난 주문 내역 확인 완료")
                .resultData(res)
                .build();
    }

    @PutMapping("v3/upw")
    @Operation(summary = "비밀번호 변경")
    public ResultResponse<Integer> patchUpw(@Valid @RequestBody UserUpwPatchReq p) {
        int res = userService.patchUpw(p);
        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("비밀번호 변경이 완료되었습니다.")
                .resultData(res)
                .build();
    }

    @GetMapping("orderId")
    @Operation(summary = "userId로 최신 orderId 주기")
    public ResultResponse<Long> getSignedUserGetOrder(long userId){
        long res = userService.getSignedUserGetOrder(userId);

        return ResultResponse.<Long>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("오더 PK 받기")
                .resultData(res)
                .build();
    }

    /*@GetMapping("alert")
    @Operation(summary = "메인 페이지 사용자 알람 데이터")
    public ResultResponse<List<UserAlertDto>> getUserAlert(@Valid @RequestParam long userId) {
        List<UserAlertDto> res = userService.getUserAlert(userId);
        return ResultResponse.<List<UserAlertDto>>builder()
                .statusCode("200")
                .resultMsg("알람 조회가 완료되었습니다.")
                .resultData(res)
                .build();
    }*/


    @PutMapping("v3/find-password")
    @Operation(summary = "비밀번호 찾기")
    public ResultResponse<Integer> findPassword(@Valid @RequestBody UserFindPasswordReq p) {
        int result = userService.findPassword(p);
        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("비밀번호 찾기가 완료 되었습니다.")
                .resultData(result)
                .build();
    }

    @PatchMapping("v3/userInfo")
    @Operation(summary = "회원 정보 등록", description = "닉네임, 핸드폰 번호, 프로필 사진 등록 및 수정")
    public ResultResponse<User> updateUser(@RequestPart("req") @Valid UserUpdateInfoReq req,
                                           @RequestPart("userPic") MultipartFile userPic) {
        /*UserUpdateInfoReq req = new UserUpdateInfoReq();
        req.setNickName(nickName);
        req.setPhone(phone);*/

        User updatedUser = userService.updateUserInfo(req, userPic);
        return ResultResponse.<User>builder()
                .statusCode("200")
                .resultMsg("회원 정보 등록 완료")
                .resultData(updatedUser)
                .build();
    }

    @PostMapping("v3/review")
    @Operation(summary = "리뷰 등록")
    public ResultResponse<Review> postReview(
            @RequestPart("reviewRequestDto") @Valid ReviewRequestDto reviewRequestDto,
            @RequestPart(required = false) List<MultipartFile> reviewPics) throws Exception {

        Review review = reviewService.postReview(reviewRequestDto, reviewPics);

        return ResultResponse.<Review>builder()
                .statusCode("200")
                .resultMsg("리뷰 등록 성공")
                .resultData(review)
                .build();
    }

    @GetMapping("v3/review")
    @Operation(summary = "사용자 리뷰 조회", description = "사용자가 본인이 작성한 리뷰를 조회")
    public ResultResponse<List<GetReviewRes>> getReview(@Valid @ParameterObject GetReviewReq p) {
        try {
            List<GetReviewRes> list = reviewService.getReview(p);

            return ResultResponse.<List<GetReviewRes>>builder()
                    .statusCode("200")
                    .resultMsg("리뷰 조회가 완료되었습니다.")
                    .resultData(list)
                    .build();
        } catch (RuntimeException e) {
            return ResultResponse.<List<GetReviewRes>>builder()
                    .statusCode("400")
                    .resultMsg(e.getMessage())
                    .build();
        }
    }

    @DeleteMapping("v3/review")
    @Operation(summary = "리뷰 삭제")
    public ResultResponse<?> delReview(
            @Parameter(description = "삭제할 리뷰의 주문 ID", required = true)
            @RequestParam Long orderId) throws IOException {
        reviewService.delReview(orderId);
        return ResultResponse.builder()
                .statusCode("200")
                .resultMsg("리뷰 삭제 성공")
                .build();
    }

    /*@PostMapping("ticket")
    @Operation(summary = "티켓생성")
    public ResultResponse<Long> postTicket(@RequestBody PostTicketReq p){
        userPaymentMemberService.postTicket(p);

        return ResultResponse.<Long>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("티켓생성완료")
                .resultData(p.getTicketId())
                .build();
    }*/

    //2차 기능 개선---------------------------------------------------------------------------------
    @GetMapping("/alert")
    @Operation(summary = "사용자 알람 데이터")
    public ResultResponse<List<UserAlertDto>> getUserAlertV3(@Valid @RequestParam long userId) {
        List<UserAlertDto> res = userService.getUserAlertV3(userId);
        return ResultResponse.<List<UserAlertDto>>builder()
                .statusCode("200")
                .resultMsg("알람 조회가 완료되었습니다.")
                .resultData(res)
                .build();
    }

    //2차 기능 개선---------------------------------------------------------------------------------
    /*@GetMapping("v3/company/user")
    @Operation(summary = "함께 결재할 인원 조회")
    public ResultResponse<List<CompanyUserGetRes>> getCompanyUser(@ParameterObject @ModelAttribute @Valid CompanyUserGetReq req) {

        List<CompanyUserGetRes> resList = userService.getCompanyUser(req);

        return ResultResponse.<List<CompanyUserGetRes>>builder()
                .statusCode("200")
                .resultMsg("결제 인원 검색 완료")
                .resultData(resList)
                .build();
    }*/

    @GetMapping()
    @Operation(summary = "회원 정보 조회")
    public ResultResponse<?> getUserV3() {
        UserGetRes result = userService.getUserV3();

        return ResultResponse.<UserGetRes>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("회원 정보 조회 성공")
                .resultData(result)
                .build();
    }
}
