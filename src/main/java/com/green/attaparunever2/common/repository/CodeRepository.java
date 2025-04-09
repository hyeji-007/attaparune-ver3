package com.green.attaparunever2.common.repository;

import com.green.attaparunever2.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends JpaRepository<Code, String> {
    Code findByCode(String code);
}
