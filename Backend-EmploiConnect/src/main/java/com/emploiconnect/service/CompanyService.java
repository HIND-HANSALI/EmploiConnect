package com.emploiconnect.service;

import com.emploiconnect.dto.request.CompanyRequestDto;
import com.emploiconnect.dto.response.CompanyResponseDto;
import com.emploiconnect.entity.Company;
import com.emploiconnect.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface CompanyService {

    Company save(Company company, boolean isSeed);
    Optional<Company> findDefaultCompany();
    CompanyResponseDto createCompany(CompanyRequestDto companyDto);
    List<CompanyResponseDto> getAllCompanies();
    CompanyResponseDto getCompanyById(Long id);
    CompanyResponseDto updateCompany(CompanyRequestDto companyDto, Long id);
    void deleteCompany(Long id);
}
