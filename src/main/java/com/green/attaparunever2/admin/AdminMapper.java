package com.green.attaparunever2.admin;

import com.green.attaparunever2.admin.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    int insAdmin(AdminSignUpReq p);
    AdminSignInRes selAdminByAid(String uId);
    AdminGetRes selAdminByAdminId(long userId);
    AdminDTO selAdminByEmailAndName(AdminFindIdReq req);
    int delAdmin(long adminId);
    int insAdminEmailVerification(AdminMailVerificationDTO dto);
    AdminMailVerificationDTO selAdminEmailVerificationByAdminId(long adminId);
    AdminMailVerificationDTO selAdminEmailVerificationByAId(String uId);
    int delAdminEmailVerification(long adminId);

    int patchUpw(AdminUpwPatchReq p);


    List<getCompanyPaymentRes> getCompanyPayment(SelCompanyPaymentReq req);
    int getCompanyPaymentTotalCount();
    int signAdmin(SignAdminReq p);
    SignInAdminRes signInAdminByAid(String aId);
    List<SelRefundRes> selRefund(GetRefundReq req);
    int selRefundTotalCount();
    List<SelCompanyEnrollmentRes> selCompanyEnrollment(GetCompanyAndRestaurantEnrollmentReq req);
    int selCompanyEnrollmentTotalCount();
    List<SelRestaurantEnrollmentRes> selRestaurantEnrollment(GetCompanyAndRestaurantEnrollmentReq req);
    int selRestaurantEnrollmentTotalCount();
    SelOneSystemPostRes selOneSystemPost(SystemPostDetailGetReq req);
    List<SelSystemPostRes> selSystemBoard();
    List<SelSystemPostRes> selSystemPost(int startIdx, Integer size);
    int selPostCount();

    List<SelQuestionPostRes> selQuestionBoard(SelQuestionPostReq p);
    int selQuestionCount();
}
