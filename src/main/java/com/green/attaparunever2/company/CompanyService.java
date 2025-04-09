package com.green.attaparunever2.company;

import com.green.attaparunever2.admin.AdminRepository;
import com.green.attaparunever2.admin.company.model.AdminCompanyEmployeeGetRes;
import com.green.attaparunever2.common.DateTimeUtils;
import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.common.repository.CodeRepository;
import com.green.attaparunever2.company.model.*;
import com.green.attaparunever2.config.jwt.JwtUser;
import com.green.attaparunever2.config.security.AuthenticationFacade;
import com.green.attaparunever2.entity.*;
import com.green.attaparunever2.user.UserEmailVerificationRepository;
import com.green.attaparunever2.user.UserMapper;
import com.green.attaparunever2.user.UserPointDepositRepository;
import com.green.attaparunever2.user.UserRepository;
import com.green.attaparunever2.user.model.UserMailVerificationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyMapper companyMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserEmailVerificationRepository userEmailVerificationRepository;
    private final CompanyRepository companyRepository;
    private final CodeRepository codeRepository;
    private final AdminRepository adminRepository;
    private final UserPointDepositRepository userPointDepositRepository;
    private final RefundRepository refundRepository;
    private final AuthenticationFacade authenticationFacade;

    //Status API 호출 URL
    private String STATUS_URL = "https://api.odcloud.kr/api/nts-businessman/v1/status";
    //Encoding 서비스키
//    private String SERVICE_KEY = "VukMM9i5Y7Mk%2F8YKm512TGLzUvx52TA1JycK%2BcdzZx1O6yfWkanTKlPznHV%2Fb6p2ENnjMK1U9FdgztyxUVpVdQ%3D%3D";
    //Decoding 서비스키
    private String SERVICE_KEY = "VukMM9i5Y7Mk/8YKm512TGLzUvx52TA1JycK+cdzZx1O6yfWkanTKlPznHV/b6p2ENnjMK1U9FdgztyxUVpVdQ==";
    //Return 타입 JSON/XML
    private String RETURN_TYPE = "JSON";

    public CompanyStatusRes status(CompanyStatusReq req) {
        Map<String, List<String>> reqBody = new HashMap<>();
        reqBody.put("b_no", List.of(req.getBNo())); // 요청받은 b_no를 리스트로 생성

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 서비스 키 인코딩 및 URI 생성
        String encodedServiceKey;
        URI uri;
        try {
            encodedServiceKey = URLEncoder.encode(SERVICE_KEY, StandardCharsets.UTF_8.toString());
            uri = new URI(STATUS_URL + "?serviceKey=" + encodedServiceKey);
            log.info("Encoded URI: {}", uri);
        } catch (Exception e) {
            log.error("URI 생성 실패", e);
            throw new RuntimeException("URI 생성 중 오류 발생", e);
        }
        HttpEntity<Map<String, List<String>>> entity = new HttpEntity<>(reqBody, headers);

        //API 호출
        ResponseEntity<Map> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                entity,
                Map.class
        );

        // 응답 데이터 확인
        log.info("Response Status Code: {}", response.getStatusCode());
        log.info("Response Headers: {}", response.getHeaders());
        log.info("Response Body: {}", response.getBody());

        //결과값 parse 후 res에 맞게 매핑
        Map<String, Object> responseBody = response.getBody();
        CompanyStatusRes companyStatusRes = new CompanyStatusRes();

        try {
            if (responseBody != null && responseBody.containsKey("data")) {
                List<Map<String, Object>> data = (List<Map<String, Object>>) responseBody.get("data");
                if (!data.isEmpty()) {
                    Map<String, Object> firstResult = data.get(0);
                    companyStatusRes.setStatusCode(response.getStatusCode().toString());
                    companyStatusRes.setStatusCodeNm((String) responseBody.get("status_code"));
                    companyStatusRes.setBNo((String) firstResult.get("b_no"));
                    companyStatusRes.setBStt((String) firstResult.get("b_stt"));
                    companyStatusRes.setBSttCd((String) firstResult.get("b_stt_cd"));
                    companyStatusRes.setEndDt((String) firstResult.get("end_dt"));
                }
            }

//            if("01".equals(companyStatusRes.getBSttCd()))
//            {
//                정상적인 사업일 경우 사용하고싶은데로 코딩
//            } else {
//                02, 03이 있을건데 코딩을하느냐, 마느냐
//            }

//            "b_no" -> "3848801297"
//            "b_stt" -> "계속사업자"
//            "b_stt_cd" -> "01"
//            "tax_type" -> "부가가치세 일반과세자"
//            "tax_type_cd" -> "01"
//            "end_dt" -> ""
//            "utcc_yn" -> "N"
//            "tax_type_change_dt" -> ""
//            "invoice_apply_dt" -> ""
//            "rbf_tax_type" -> "해당없음"
//            "rbf_tax_type_cd" -> "99"

            log.debug("api 호출 후 결과:: "+companyStatusRes.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return companyStatusRes;

    }

    @Transactional
    public int postEmployee(SignUpEmployeeReq req) {
        // 회사 정보 조회
        Company company = companyRepository.findById(req.getCompanyId()).orElse(null);
        if (company == null) {
            throw new CustomException("회사를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
        // 코드 조회
        Code code = codeRepository.findById("00104").orElse(null);
        if (code == null) {
            throw new CustomException("코드를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        // 관리자 권한 조회
        Long signedAdminId = authenticationFacade.getSignedUserId();
        if (!signedAdminId.equals(req.getAdminId())) {
            throw new CustomException("로그인한 관리자 계정과 일치하지 않는 관리자 정보입니다.", HttpStatus.BAD_REQUEST);
        }

        // uid 설정
        String companyCd = company.getCompanyCd();
        String uid = companyCd + req.getEmployeeNum();

        // 인증정보 조회(만약 인증이 만료된 아이디로 가입하려는 경우 인증 정보를 지우고 유저 정보도 지워야 함)
        UserMailVerificationDTO userMailVerificationDTO = userMapper.selUserEmailVerificationByUId(uid);

        if(userMailVerificationDTO != null) {
            LocalDateTime now = LocalDateTime.now();

            // 인증 기간이 만료되었다면 인증, 회원정보 삭제
            if (now.isAfter(DateTimeUtils.convertToLocalDateTime(userMailVerificationDTO.getExpiredDate()))) {
                userEmailVerificationRepository.deleteById(userMailVerificationDTO.getUserId());
                userRepository.deleteById(userMailVerificationDTO.getUserId());
            }
        }

        // 비밀번호 암호화
        String hashedPassword = BCrypt.hashpw(req.getUpw(), BCrypt.gensalt());

        // 유저 정보 설정
        User user = new User();
        user.setCompany(company);
        user.setName(req.getName());
        user.setPhone(req.getPhone());
        user.setEmail(req.getEmail());
        user.setUid(uid);
        user.setUpw(hashedPassword);
        user.setCode(code);

        userRepository.save(user);

        return 1;
    }

    public AdminCompanyEmployeeGetRes getEmployee(GetEmployeeReq req) {
        AdminCompanyEmployeeGetRes res = new AdminCompanyEmployeeGetRes();
        List<GetEmployeeRes> list = companyMapper.selEmployee(req);
        int listTotalCount = companyMapper.selEmployeeCount(req);
        int totalPageCount = (int)Math.ceil((double)listTotalCount / req.getSize());

        // 관리자 권한 조회
        Long signedAdminId = authenticationFacade.getSignedUserId();
        if (!signedAdminId.equals(req.getAdminId())) {
            throw new CustomException("로그인한 관리자 계정과 일치하지 않는 관리자 정보입니다.", HttpStatus.BAD_REQUEST);
        }

        log.info("req: {}", req.toString());
        if (list == null || list.isEmpty()) {
            throw new CustomException("조건에 맞는 사원이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        res.setEmployeeList(list);
        res.setTotalPageCount(totalPageCount);

        return res;
    }

    @Transactional
    public int patchCompany(UpdCompanyReq req) {
        // 회사 확인 절차
        Company company = companyRepository.findById(req.getCompanyId())
                .orElseThrow(() -> new CustomException("회사를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        // 관리자 확인 절차
        Admin admin = adminRepository.findById(req.getAdminId())
                .orElseThrow(() -> new CustomException("관리자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        // 관리자 권한 조회
        Long signedAdminId = authenticationFacade.getSignedUserId();
        if (!signedAdminId.equals(req.getAdminId())) {
            throw new CustomException("로그인한 관리자 계정과 일치하지 않는 관리자 정보입니다.", HttpStatus.BAD_REQUEST);
        }

        // 관리자 권한 확인
        if (!company.getCompanyId().equals(admin.getDivisionId())) {
            throw new CustomException("이 회사의 관리자 권한이 없습니다.", HttpStatus.BAD_REQUEST);
        }

        if (req.getName() != null) {
            company.setName(req.getName());
        }
        if (req.getAddress() != null) {
            company.setAddress(req.getAddress());
        }
        if (req.getCeoName() != null) {
            company.setCeoName(req.getCeoName());
        }

        companyRepository.save(company);

        return 1;
    }

    @Transactional
    public int patchEmployeePointCollect(UpdEmployeePointCollectReq req) {
        Admin admin = adminRepository.findById(req.getAdminId())
                .orElseThrow(() -> new CustomException("관리자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        // 관리자 권한 조회
        Long signedAdminId = authenticationFacade.getSignedUserId();
        if (!signedAdminId.equals(req.getAdminId())) {
            throw new CustomException("로그인한 관리자 계정과 일치하지 않는 관리자 정보입니다.", HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByUserId(req.getUserId())
                .orElseThrow(() -> new CustomException("사원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        Code code = codeRepository.findById("00302").orElse(null);

        // 회사 관리자 권한 확인
        if (!admin.getDivisionId().equals(user.getCompany().getCompanyId())) {
            throw new CustomException("이 사원에 대한 권한이 없습니다.", HttpStatus.BAD_REQUEST);
        }

        // 포인트 업데이트
        if (req.getPoint() != 0) {
            // 유저의 포인트 차감
            int userPoint = user.getPoint() - req.getPoint();
            if (userPoint < 0) {
                throw new CustomException("사원의 포인트가 부족합니다.", HttpStatus.BAD_REQUEST);
            }
            user.setPoint(userPoint);
            userRepository.save(user);

            // 유저 입금 정보 튜플 생성
            UserPointDeposit userPointDeposit = new UserPointDeposit();
            userPointDeposit.setUser(user);
            userPointDeposit.setAdmin(admin);
            userPointDeposit.setCode(code);
            userPointDeposit.setPointAmount(req.getPoint());
            userPointDepositRepository.save(userPointDeposit);

            // 회사 포인트 추가
            Company company = user.getCompany();
            int companyPoint = company.getCurrentPoint() + req.getPoint();
            company.setCurrentPoint(companyPoint);
            companyRepository.save(company);

        }

        return 1;
    }

    @Transactional
    public int patchEmployee(UpdEmployeeReq req) {
        Admin admin = adminRepository.findById(req.getAdminId())
                .orElseThrow(() -> new CustomException("관리자 정보가 일치하지 않습니다.", HttpStatus.NOT_FOUND));

        // 관리자 권한 조회
        Long signedAdminId = authenticationFacade.getSignedUserId();
        if (!signedAdminId.equals(req.getAdminId())) {
            throw new CustomException("로그인한 관리자 계정과 일치하지 않는 관리자 정보입니다.", HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByUserId(req.getUserId())
                .orElseThrow(() -> new CustomException("사용자 정보가 일치하지 않습니다.", HttpStatus.NOT_FOUND));

        Code code = codeRepository.findById("00302").orElse(null);


        if (!admin.getDivisionId().equals(user.getCompany().getCompanyId())) {
            throw new CustomException("이 사원에 대한 권한이 없습니다.", HttpStatus.BAD_REQUEST);
        }

        // 유저 포인트 회수
        if (user.getPoint() > 0) {
            UserPointDeposit userPointDeposit = new UserPointDeposit();
            userPointDeposit.setAdmin(admin);
            userPointDeposit.setUser(user);
            userPointDeposit.setPointAmount(user.getPoint());
            userPointDeposit.setCode(code);
            userPointDepositRepository.save(userPointDeposit);
        }

        // 유저 상태 변경
        user.setActivation(req.getActivation());

        Company company = user.getCompany();
        int companyPoint = company.getCurrentPoint() + (user.getPoint() > 0 ? user.getPoint() : 0);
        company.setCurrentPoint(companyPoint);
        companyRepository.save(company);

        int userPoint = 0;
        user.setPoint(userPoint);
        userRepository.save(user);


        return 1;
    }

    public int postRefund(InsRefundReq req) {
        // 관리자 PK 조회
        Admin admin = adminRepository.findById(req.getAdminId())
                .orElseThrow(() -> new CustomException("관리자 정보가 일치하지 않습니다.", HttpStatus.BAD_REQUEST));

        // 회사 조회
        Company company = companyRepository.findById(admin.getDivisionId())
                .orElseThrow(() -> new CustomException("회사 정보가 일치하지 않습니다.", HttpStatus.BAD_REQUEST));

        // 회사 포인트 비교
        if (company.getCurrentPoint() < req.getRefundPoint()) {
            throw new CustomException("환불하려는 포인트보다 보유중인 포인트가 더 적습니다.", HttpStatus.BAD_REQUEST);
        }

        // 관리자 권한 조회
        Long signedAdminId = authenticationFacade.getSignedUserId();
        if (!signedAdminId.equals(req.getAdminId())) {
            throw new CustomException("로그인한 관리자 계정과 일치하지 않는 관리자 정보입니다.", HttpStatus.BAD_REQUEST);
        }

        Refund refund = new Refund();

        refund.setAdmin(admin);
        refund.setRefundPoint(req.getRefundPoint());
        refund.setRefundDetail(req.getRefundDetail());
        refund.setRefundAmount(req.getRefundPoint() - ((int)(req.getRefundPoint() * 0.1)));
        refundRepository.save(refund);

        return 1;
    }

    public int delRefund(DelRefundReq req) {
        Refund refund = refundRepository.findById(req.getRefundId())
                .orElseThrow(() -> new CustomException("환불 요청 정보가 없습니다.", HttpStatus.NOT_FOUND));

        // 관리자 권한 조회
        Long signedAdminId = authenticationFacade.getSignedUserId();
        if (!signedAdminId.equals(req.getAdminId())) {
            throw new CustomException("로그인한 관리자 계정과 일치하지 않는 관리자 정보입니다.", HttpStatus.BAD_REQUEST);
        }

        if (refund.getRefundYn() != 0) {
            throw new CustomException("해당 환불 요청이 이미 처리되었습니다.", HttpStatus.BAD_REQUEST);
        }

        refundRepository.delete(refund);

        return 1;
    }
}
