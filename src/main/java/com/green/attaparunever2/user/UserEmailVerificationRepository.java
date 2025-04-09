package com.green.attaparunever2.user;

import com.green.attaparunever2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmailVerificationRepository extends JpaRepository<User, Long> {

}
