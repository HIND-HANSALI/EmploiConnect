package com.emploiconnect.service.impl;

import com.emploiconnect.dto.request.CompanyRequestDto;
import com.emploiconnect.dto.response.CompanyResponseDto;
import com.emploiconnect.entity.Company;
import com.emploiconnect.entity.Offer;
import com.emploiconnect.handler.exception.ResourceNotFoundException;
import com.emploiconnect.handler.request.CustomException;
import com.emploiconnect.repository.CompanyRepository;
import com.emploiconnect.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    @Override
    public Optional<Company> findDefaultCompany() {return companyRepository.findByIsDefaultTrue();}

    @Override
    public CompanyResponseDto createCompany(CompanyRequestDto companyDto) {
        Company company = modelMapper.map(companyDto, Company.class);
        Company savedCompany = companyRepository.save(company);
        return modelMapper.map(savedCompany, CompanyResponseDto.class);
    }

    @Override
    public List<CompanyResponseDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(company -> modelMapper.map(company, CompanyResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyResponseDto getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
        return modelMapper.map(company, CompanyResponseDto.class);
    }

    @Override
    public CompanyResponseDto updateCompany(CompanyRequestDto companyDto, Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));

        company.setName(companyDto.getName());
        company.setSector(companyDto.getSector());
        company.setLocation(companyDto.getLocation());
        company.setFoundationDate(companyDto.getFoundationDate());
        company.setSpecializations(companyDto.getSpecializations());
        company.setDescription(companyDto.getDescription());

        Company updatedCompany = companyRepository.save(company);
        return modelMapper.map(updatedCompany, CompanyResponseDto.class);
    }

    @Override
    public void deleteCompany(Long id) {

        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
        companyRepository.delete(existingCompany);
    }

    @Override
    public Company save(Company company, boolean isSeed){
        if (findDefaultCompany().isPresent() && company.isDefault()) {
            throw new CustomException("There is already a default campany", HttpStatus.UNAUTHORIZED);
        }else{
            return companyRepository.save(company);
        }

    }
}
