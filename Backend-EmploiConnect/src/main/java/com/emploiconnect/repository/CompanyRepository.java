package com.emploiconnect.repository;
import com.emploiconnect.entity.Company;
import com.emploiconnect.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByIsDefaultTrue();
}
