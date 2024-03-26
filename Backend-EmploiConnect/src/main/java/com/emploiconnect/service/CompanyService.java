package com.emploiconnect.service;

import com.emploiconnect.entity.Company;
import com.emploiconnect.entity.Role;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public interface CompanyService {

    Company save(Company company, boolean isSeed);
    Optional<Company> findDefaultCompany();
}
