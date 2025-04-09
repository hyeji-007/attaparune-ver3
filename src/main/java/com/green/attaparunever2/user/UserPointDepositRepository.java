package com.green.attaparunever2.user;

import com.green.attaparunever2.entity.User;
import com.green.attaparunever2.entity.UserPointDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPointDepositRepository extends JpaRepository<UserPointDeposit, Long> {
    Optional<UserPointDeposit> findByUser(User user);
}
