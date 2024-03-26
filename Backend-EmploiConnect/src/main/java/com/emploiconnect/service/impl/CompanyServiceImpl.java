package com.emploiconnect.service.impl;

import com.emploiconnect.entity.Company;
import com.emploiconnect.handler.request.CustomException;
import com.emploiconnect.repository.CompanyRepository;
import com.emploiconnect.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    @Override
    public Optional<Company> findDefaultCompany() {return companyRepository.findByIsDefaultTrue();}
    @Override
    public Company save(Company company, boolean isSeed){
        if (findDefaultCompany().isPresent() && company.isDefault()) {
            throw new CustomException("There is already a default campany", HttpStatus.UNAUTHORIZED);
        }else{
            return companyRepository.save(company);
        }

    }
}
