package com.green.attaparunever2.user;

import com.green.attaparunever2.entity.Code;
import com.green.attaparunever2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(Long userId);

    @Query("SELECT a.userId FROM User a WHERE a.uid = :uid AND a.email = :email")
    Long findUserIdByUidAndEmail(@Param("uid") String uid, @Param("email") String email);

    boolean existsByCode_Code(String code);

    @Query("SELECT u.code FROM User u WHERE u.userId = :userId")
    Optional<Code> findRoleCodeById(Long userId);
}
