package com.green.attaparunever2.admin.company;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.attaparunever2.admin.AdminMapper;
import com.green.attaparunever2.admin.AdminRepository;
import com.green.attaparunever2.admin.company.model.*;
import com.green.attaparunever2.admin.model.AdminGetReq;
import com.green.attaparunever2.admin.model.AdminGetRes;
import com.green.attaparunever2.common.delayed.DelayedTaskScheduler;
import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.common.repository.CodeRepository;
import com.green.attaparunever2.common.util.PaymentUtils;
import com.green.attaparunever2.company.CompanyMapper;
import com.green.attaparunever2.company.CompanyRepository;
import com.green.attaparunever2.config.security.AuthenticationFacade;
import com.green.attaparunever2.entity.*;
import com.green.attaparunever2.user.UserPointDepositRepository;
import com.green.attaparunever2.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AdminCompanyService {
    private final static double POINT_PERCENTAGE = 0.05;  // 포인트 추가 입금 퍼센트(5%)

    private final PaymentInfoTmpRepository paymentInfoTmpRepository;
    private final CompanyRepository companyRepository;
    private final AdminMapper adminMapper;
    private final PaymentUtils paymentUtils;
    private final CompanyMapper companyMapper;
    private final AdminRepository adminRepository;
    private final AuthenticationFacade authenticationFacade;
    private final UserRepository userRepository;
    private final UserPointDepositRepository userPointDepositRepository;
    private final CodeRepository codeRepository;
    private final CompanyPointDepositRepository companyPointDepositRepository;
    private final DelayedTaskScheduler scheduler;
    private final AdminCompanyMapper adminCompanyMapper;

    // 포인트 구매 결재전 결재 정보 임시 저장
    @Transactional
    public PaymentInfoTmp postPaymentTemp(AdminCompanyPaymentTempPostReq req) {
        PaymentInfoTmp paymentInfoTmp = new PaymentInfoTmp();

        paymentInfoTmp.setOrderId(req.getOrderId());
        paymentInfoTmp.setAmount(req.getAmount());

        return paymentInfoTmpRepository.save(paymentInfoTmp);
    }

    // 회사 포인트 구매
    @Transactional
    public ResponseEntity patchPoint(HttpServletRequest request, String jsonBody) throws Exception {
        // JSON 문자열을 자바 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonBody);  // JsonNode로 변환

        long adminId = 1L; //authenticationFacade.getSignedUserId();
        String paymentKey = jsonNode.get("paymentKey").asText();
        int amount = jsonNode.get("amount").asInt();
        String orderId = jsonNode.get("orderId").asText();

        // 01. 결재요청 정보 일치하는지 여부 검사 ----------------------
        PaymentInfoTmp paymentInfoTmp = paymentInfoTmpRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 orderId가 존재하지 않습니다."));

        if(paymentInfoTmp.getAmount() != amount) {
            throw new CustomException("결재 금액이 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 추출한 데이터를 새로운 JSON 문자열로 변환
        JsonNode newJsonNode = objectMapper.createObjectNode()
                .put("paymentKey", jsonNode.get("paymentKey").asText())
                .put("orderId", jsonNode.get("orderId").asText())
                .put("amount", jsonNode.get("amount").asInt());

        // 새로운 JSON 객체를 JSON 문자열로 변환
        String newJsonString = objectMapper.writeValueAsString(newJsonNode);
        System.out.println("New JSON String: " + newJsonString);


        // 03. 토스 서버로 결재 승인 요청 ----------------------
        String secretKey = request.getRequestURI().contains("/confirm/payment") ? PaymentUtils.API_SECRET_KEY : PaymentUtils.WIDGET_SECRET_KEY;
        JSONObject response = paymentUtils.sendRequest(paymentUtils.parseRequestData(newJsonString), secretKey, "https://api.tosspayments.com/v1/payments/confirm");
        int statusCode = response.containsKey("error") ? 400 : 200;


        // 04. 결재가 성공적으로 완료된 경우 결재 정보 DB 저장 ----------------------
        if(statusCode == 200) {
            // 결재정보 DB 저장
            CompanyPointDeposit companyPointDeposit = new CompanyPointDeposit();

            Admin admin = adminRepository.findById(adminId).orElseThrow();

            companyPointDeposit.setAdminId(admin);
            companyPointDeposit.setCashAmount(amount);
            companyPointDeposit.setPointAmount(companyPointDeposit.getCashAmount() + ((int)(amount * POINT_PERCENTAGE)));
            companyPointDeposit.setPaymentKey(paymentKey);

            companyPointDepositRepository.save(companyPointDeposit);

            // 포인트 회사에 입금 처리
            Company company = companyRepository.findById(admin.getDivisionId()).orElseThrow();

            company.setCurrentPoint(company.getCurrentPoint() + companyPointDeposit.getPointAmount());

            companyRepository.save(company);
        }

        return ResponseEntity.status(statusCode).body(response);
    }

    // 회사 >> 사용자 포인트 입금
    @Transactional
    public void patchPointUser(AdminCompanyUserPointPatchReq req) {
        Long adminId = authenticationFacade.getSignedUserId();

        // 어드민 정보 가져옴
        Admin admin = adminRepository.findById(adminId).orElseThrow();

        Company company = companyRepository.findById(admin.getDivisionId()).orElseThrow();

        // 포인트가 있는지 여부 검사
        if(company.getCurrentPoint() < req.getPointAmount()) {
            throw new CustomException("입금할 포인트가 부족합니다.", HttpStatus.BAD_REQUEST);
        }

        // 사용자 정보 가져옴
        User user = userRepository.findById(req.getUserId()).orElseThrow();

        // 사용자에게 포인트 입금
        user.setPoint(user.getPoint() + req.getPointAmount());

        // 회사 정보 포인트 출금 처리
        int companyAmount = company.getCurrentPoint() - req.getPointAmount();

        company.setCurrentPoint(companyAmount);

        // 회사 정보 저장
        companyRepository.save(company);

        // 사용자 포인트 입금 정보 생성
        UserPointDeposit userPointDeposit = new UserPointDeposit();

        userPointDeposit.setAdmin(admin);
        userPointDeposit.setUser(user);
        userPointDeposit.setPointAmount(req.getPointAmount());

        Code code = codeRepository.findById("00301").orElseThrow();
        userPointDeposit.setCode(code);

        userPointDepositRepository.save(userPointDeposit);
        
        long createdDepositId = userPointDeposit.getDepositId();

        // 포인트 3개월 뒤에 환수 딜레이 큐 설정
        scheduler.schedule(() -> {
            UserPointDeposit createdUserPointDeposit = userPointDepositRepository.findById(createdDepositId).orElseThrow();
            
            // 사용자 포인트 마이너스
            User refundUser = userRepository.findById(req.getUserId()).orElseThrow();

            refundUser.setPoint(refundUser.getPoint() - createdUserPointDeposit.getPointAmount());

            userRepository.save(refundUser);

            // 회사 포인트 플러스
            Company refundCompany = companyRepository.findById(admin.getDivisionId()).orElseThrow();

            // 회사 정보 포인트 출금 처리
            refundCompany.setCurrentPoint(refundCompany.getCurrentPoint() + createdUserPointDeposit.getPointAmount());

            // 회사 정보 저장
            companyRepository.save(refundCompany);
            
            // 사용자 포인트 회수 정보 생성
            UserPointDeposit refundUserPointDeposit = new UserPointDeposit();
            
            refundUserPointDeposit.setAdmin(admin);
            refundUserPointDeposit.setUser(refundUser);
            refundUserPointDeposit.setPointAmount(createdUserPointDeposit.getPointAmount());

            Code refundCode = codeRepository.findById("00302").orElseThrow();
            refundUserPointDeposit.setCode(refundCode);

            userPointDepositRepository.save(refundUserPointDeposit);
        }, 90, TimeUnit.DAYS);

    }

    // 관리자 정보 조회
    public List<AdminCompanyPointHistory> getCompanyPointHistoryByAdminId() {
        Long adminId = authenticationFacade.getSignedUserId();

        List<AdminCompanyPointHistory> resList = adminCompanyMapper.selCompanyPointHistoryByAdminId(adminId);

        return resList;
    }

    //포인트 구매 이력
    public CompanyPurchaseHistoryGetRes getPurchaseHistory(SelPurchaseHistoryReq req){
        CompanyPurchaseHistoryGetRes res = new CompanyPurchaseHistoryGetRes();
        List<SelPurchaseHistoryRes> resList = adminCompanyMapper.selPurchaseHistory(req);
        int totalListCount = adminCompanyMapper.selPurchaseHistoryTotalCount(req);
        int totalPageCount = (int)Math.ceil((double)totalListCount / req.getSize());

        res.setTotalPageCount(totalPageCount);
        res.setHistoryList(resList);

        return res;
    }

    //포인트 입금 내역
    public CompanyDepositHistoryGetRes getDepositDetail(SelDepositDetailReq req){
        CompanyDepositHistoryGetRes res = new CompanyDepositHistoryGetRes();
        List<SelDepositDetailRes> resList = adminCompanyMapper.selDepositDetail(req);
        int totalListCount = adminCompanyMapper.selDepositTotalCount(req);
        int totalPageCount = (int)Math.ceil((double)totalListCount / req.getSize());

        res.setHistoryList(resList);
        res.setTotalPageCount(totalPageCount);

        return res;
    }

    // 관리자 & 회사 정보 조회
    public SelCompanyAndAdminInfoRes getCompanyAndAdminInfo(SelCompanyAndAdminInfoReq req){
        Long adminId = authenticationFacade.getSignedUserId();
        if (!adminId.equals(req.getAdminId())) {
            throw new CustomException("정보를 조회할 권한이 없습니다.", HttpStatus.BAD_REQUEST);
        }

        SelCompanyAndAdminInfoRes res = adminCompanyMapper.selCompanyAndAdminInfo(req);

        return res;
    }
}
