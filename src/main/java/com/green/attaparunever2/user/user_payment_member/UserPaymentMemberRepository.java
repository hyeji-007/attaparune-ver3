package com.green.attaparunever2.user.user_payment_member;

import com.green.attaparunever2.entity.UserPaymentMember;
import com.green.attaparunever2.entity.UserPaymentMemberIds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserPaymentMemberRepository extends JpaRepository<UserPaymentMember, UserPaymentMemberIds> {
    @Modifying
    @Transactional
    @Query("DELETE FROM UserPaymentMember upm WHERE upm.userPaymentMemberIds.orderId = :orderId")
    int deleteByOrderIdAndUserId(@Param("orderId") Long orderId);
}
