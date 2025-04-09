package com.green.attaparunever2.company;

import com.green.attaparunever2.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(value = "SELECT company_cd FROM company ORDER BY company_cd DESC LIMIT 1", nativeQuery = true)
    String findFirstByLatestCompanyCd();

    @Query(value = "SELECT company_id FROM company ORDER BY company_id DESC LIMIT 1", nativeQuery = true)
    Long findFirstByLatestCompanyId();
}
