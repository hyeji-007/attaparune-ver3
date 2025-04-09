package com.green.attaparunever2.admin;


import com.green.attaparunever2.entity.SystemPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemPostRepository extends JpaRepository<SystemPost, Long> {

}
