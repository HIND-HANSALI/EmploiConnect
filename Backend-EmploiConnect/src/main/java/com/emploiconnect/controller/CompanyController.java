package com.emploiconnect.controller;

import com.emploiconnect.dto.request.CompanyRequestDto;
import com.emploiconnect.dto.response.CompanyResponseDto;
import com.emploiconnect.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @PostMapping
    public ResponseEntity<CompanyResponseDto> createCompany(@Valid @RequestBody CompanyRequestDto companyDto) {
        CompanyResponseDto createdCompany = companyService.createCompany(companyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
    }
    @GetMapping
    public ResponseEntity<List<CompanyResponseDto>> getAllCompanies() {
        List<CompanyResponseDto> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> getCompanyById(@PathVariable Long id) {
        CompanyResponseDto company = companyService.getCompanyById(id);
        return ResponseEntity.ok(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> updateCompany(@PathVariable Long id, @Valid @RequestBody CompanyRequestDto companyDto) {
        CompanyResponseDto updatedCompany = companyService.updateCompany(companyDto, id);
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {

        companyService.deleteCompany(id);
        //return ResponseEntity.noContent().build();
    }
}
