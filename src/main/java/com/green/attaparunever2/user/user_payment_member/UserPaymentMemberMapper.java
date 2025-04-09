package com.green.attaparunever2.user.user_payment_member;

import com.green.attaparunever2.user.user_payment_member.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserPaymentMemberMapper {
    UserGetPointRes getPoint(long userId);
    int getOrderPrice(long orderId);
    int insertPaymentMember(UserPostPaymentReq p);
    int getPaymentMember(long orderId);
    UserGetPaymentInfoRes getPaymentInfo(UserGetPaymentInfoReq p);
    int patchPaymentMember(UserPatchPaymentMemberReq p); //내게 온 결제 승인 요청 수정
    int postPaymentMember(List<PostPaymentUserIdAndPoint> p); //결제 승인 요청 등록
    List<UserPaymentMemberDto> selUserPaymentMemberByOrderId(long orderId);
    UserPaymentMemberDto selUserPaymentMemberByOrderIdAndUserId(UserGetPaymentInfoReq req);
    List<SelUserOrderApprovalRes> selUserOrderApprovalAccess(long orderId);
    int insTicket(PostTicketReq p);
    int updUserPoint(int point, long userId);
    int sumMenuPrice(long orderId);
    List<SelUserPaymentMemberRes> selUserPaymentMember(long orderId);
    int updPaymentAmount(UserPaymentAmountPatchReq p);
    int deletePaymentMember(UserPaymentMemberDelReq p);
    List<PaymentMemberDto> getPaymentMemberByName(long companyId, String name, Integer size, int startIdx);

    int selUserPaymentMemberCount(long orderId, long userId);
}