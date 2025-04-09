package com.green.attaparunever2.admin;

import com.green.attaparunever2.admin.model.AdminSignUpReq;
import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.admin.model.*;
import com.green.attaparunever2.reservation.ReservationService;
import com.green.attaparunever2.reservation.model.ReservationPostReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
@RequestMapping("admin")
@Tag(name="관리자", description = "관리자 관련 API")
public class AdminController {
    private final AdminService adminService;
    private final ReservationService reservationService;

    @GetMapping
    @Operation(summary = "관리자 정보 조회")
    public ResultResponse<?> getUser(@ModelAttribute AdminGetReq req) {
        AdminGetRes result = adminService.getAdmin(req);

        return ResultResponse.<AdminGetRes>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("관리자 정보 조회 성공")
                .resultData(result)
                .build();
    }

    @GetMapping("auth-token")
    @Operation(summary = "인증번호 인증")
    public ResultResponse<?> authToken(@ModelAttribute AuthTokenReq req) {
        int result = adminService.authToken(req);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("메일 인증에 성공했습니다.")
                .resultData(result)
                .build();
    }

    @PostMapping("sign-in")
    @Operation(summary = "관리자 로그인")
    public ResultResponse<?> signInAdmin(@RequestBody SignInAdminReq p, HttpServletResponse response) {
        SignInAdminRes res = adminService.signInAdmin(p, response);

        return ResultResponse.<SignInAdminRes>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("로그인 성공")
                .resultData(res)
                .build();
    }

    @GetMapping("find-id")
    @Operation(summary = "아이디 찾기")
    public ResultResponse<Integer> findId(@ModelAttribute AdminFindIdReq p) {
        int result = adminService.findId(p);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("아이디 찾기 이메일 전송 완료")
                .resultData(result)
                .build();
    }

    @GetMapping("access-token")
    @Operation(summary = "엑세스 토큰 재발행")
    public String getAccessToken(HttpServletRequest req) {
        return adminService.getAccessToken(req);
    }

    @PutMapping("v3/upw")
    @Operation(summary = "비밀번호 변경")
    public ResultResponse<Integer> patchUpw(@Valid @RequestBody AdminUpwPatchReq p) {
        int result = adminService.patchUpw(p);
        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("비밀번호 변경이 완료되었습니다.")
                .resultData(result)
                .build();
    }

    @PutMapping("v3/find-password")
    @Operation(summary = "비밀번호 찾기")
    public ResultResponse<Integer> findPassword(@Valid @RequestBody AdminFindPasswordReq p) {
        int result = adminService.findPassword(p);
        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("비밀번호 찾기가 완료 되었습니다.")
                .resultData(result)
                .build();
    }

    @PostMapping("sign-up")
    @Operation(summary = "시스템 관리자 회원가입(백엔드 전용 컨트롤러)")
    public ResultResponse<Integer> signUpAdmin(@RequestBody SignAdminReq p) {
        int result = adminService.SignUpAdmin(p);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("시스템 관리자 회원가입 완료")
                .resultData(result)
                .build();
    }

    @PutMapping("v3")
    @Operation(summary = "식당, 회사 회원가입")
    public ResultResponse<Integer> updAdmin(@RequestBody SignUpAdminReq req){
        int result = adminService.updAdmin(req);

        return ResultResponse.<Integer>builder()
                .statusCode(HttpStatus.OK.toString())
                .resultMsg("회원가입 완료")
                .resultData(result)
                .build();
    }

    @PatchMapping("v3/coalition-request")
    @Operation(summary = "식당, 회사 제휴상태 변경 요청")
    public ResultResponse<Integer> patchAdminCoalitionState(@RequestBody UpdAdminCoalitionStateRequestReq req) {
        int result = adminService.patchAdminCoalitionState(req);

        return ResultResponse.<Integer>builder()
                .statusCode("200")
                .resultMsg("제휴상태 변경 요청 성공")
                .resultData(result)
                .build();
    }
}
