package com.green.attaparunever2.user;

import com.green.attaparunever2.user.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insUser(UserSignUpReq req);
    UserSignInRes selUserByUid(String uId);
    UserGetRes selUserByUserId(long userId);
    UserDTO selUserByEmailAndName(UserFindIdReq req);
    int delUser(long userId);
    int insUserEmailVerification(UserMailVerificationDTO dto);
    UserMailVerificationDTO selUserEmailVerificationByUserId(long userId);
    UserMailVerificationDTO selUserEmailVerificationByUId(String uId);
    int delUserEmailVerification(long userId);
    List<SelUserOrderPastCheckRes> selUserPastOrderCheck(SelUserOrderPastCheckReq p);
    UserActiveOrderRes selUserActiveOrderCheck(long userId);
    GetUserOrderVer2Res getUserOrderVer2(GetUserOrderVer2Req p);
    List<GetUserOrderMenuListDto> getUserOrderVer2MenuList(GetUserOrderVer2Req p);
    long getSignedUserGetOrder(long userId);
    int patchUpw(UserUpwPatchReq p);

    List<UserAlertDto> selUserReservationAlertByUserId(long userId);
    List<UserAlertDto> selUserPaymentAlertByUserId(long userId);
    List<UserAlertDto> selUserUserPaymentMemberAlertByUserId(long userId);
    List<CompanyUserGetRes> selCompanyUserByUserId(CompanyUserGetReq req);
    UserGetRes selUserByUserIdV3(long userId);

    List<SelUserOrderPastCheckRes> selUserOrderPastCheck(long signedUserId);
    List<SelUserOrderPastCheckDto> selUserOrderMenuPastCheck(long orderId);
}
