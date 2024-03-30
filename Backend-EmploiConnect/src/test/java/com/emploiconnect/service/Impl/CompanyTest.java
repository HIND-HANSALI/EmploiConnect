package com.emploiconnect.service.Impl;

import com.emploiconnect.dto.request.CompanyRequestDto;
import com.emploiconnect.dto.response.CompanyResponseDto;
import com.emploiconnect.entity.Company;
import com.emploiconnect.handler.exception.ResourceNotFoundException;
import com.emploiconnect.repository.CompanyRepository;
import com.emploiconnect.service.CompanyService;
import com.emploiconnect.service.impl.CompanyServiceImpl;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.web.client.ExpectedCount;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CompanyTest {

        @Mock
        private CompanyRepository companyRepositoryMock;

        @Mock
        private ModelMapper modelMapperMock;

        @InjectMocks
        private CompanyServiceImpl companyService;

        //Initialize the mocks before each test method runs
        @BeforeEach
        public void setup() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void testCreateCompany() {
            // Prepare test data
            CompanyRequestDto requestDto = new CompanyRequestDto();
            requestDto.setName("Test Company");
            requestDto.setSector("Test Sector");
            requestDto.setLocation("Test Location");
            requestDto.setFoundationDate(LocalDate.now().minusYears(1));
            requestDto.setSpecializations("Test Specializations");
            requestDto.setDescription("Test Description");

            Company company = new Company();
            company.setId(1L);
            company.setName(requestDto.getName());
            company.setSector(requestDto.getSector());
            company.setLocation(requestDto.getLocation());
            company.setFoundationDate(requestDto.getFoundationDate());
            company.setSpecializations(requestDto.getSpecializations());
            company.setDescription(requestDto.getDescription());

            CompanyResponseDto responseDto = new CompanyResponseDto();
            responseDto.setId(1L);
            responseDto.setName(requestDto.getName());
            responseDto.setSector(requestDto.getSector());
            responseDto.setLocation(requestDto.getLocation());
            responseDto.setFoundationDate(requestDto.getFoundationDate());
            responseDto.setSpecializations(requestDto.getSpecializations());
            responseDto.setDescription(requestDto.getDescription());

            when(modelMapperMock.map(requestDto, Company.class)).thenReturn(company);
            when(companyRepositoryMock.save(company)).thenReturn(company);
            when(modelMapperMock.map(company, CompanyResponseDto.class)).thenReturn(responseDto);

            // Test the method
            CompanyResponseDto createdCompany = companyService.createCompany(requestDto);

            // Verify the interactions
            verify(modelMapperMock, times(1)).map(requestDto, Company.class);
            verify(companyRepositoryMock, times(1)).save(company);
            verify(modelMapperMock, times(1)).map(company, CompanyResponseDto.class);

            // Assertions
            assertNotNull(createdCompany);
            assertEquals(responseDto.getId(), createdCompany.getId());
            assertEquals(responseDto.getName(), createdCompany.getName());
            assertEquals(responseDto.getSector(), createdCompany.getSector());
            assertEquals(responseDto.getLocation(), createdCompany.getLocation());
            assertEquals(responseDto.getFoundationDate(), createdCompany.getFoundationDate());
            assertEquals(responseDto.getSpecializations(), createdCompany.getSpecializations());
            assertEquals(responseDto.getDescription(), createdCompany.getDescription());

        }

    @Test
    public void testGetAllCompanies() {
        // Mock data
        Company company1 = new Company();
        company1.setId(1L);
        company1.setName("Company 1");

        Company company2 = new Company();
        company2.setId(2L);
        company2.setName("Company 2");

        List<Company> mockCompanies = Arrays.asList(company1, company2);

        // Mock behavior of companyRepository.findAll()    // Mock the behavior of `companyRepositoryMock` to return `mockCompanies` when `findAll()` is called.
        when(companyRepositoryMock.findAll()).thenReturn(mockCompanies);

        // Mock behavior of modelMapper.map()
        when(modelMapperMock.map(company1, CompanyResponseDto.class))
                .thenReturn(new CompanyResponseDto(1L, "Company 1", "Sector 1", "Location 1", LocalDate.now(), "Specialization 1", "Description 1"));

        when(modelMapperMock.map(company2, CompanyResponseDto.class))
                .thenReturn(new CompanyResponseDto(2L, "Company 2", "Sector 2", "Location 2", LocalDate.now(), "Specialization 2", "Description 2"));

        // Call the method under test
        List<CompanyResponseDto> result = companyService.getAllCompanies();

        // Verify the result
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Company 1", result.get(0).getName());
        assertEquals(2L, result.get(1).getId());
        assertEquals("Company 2", result.get(1).getName());
    }



    @Test
    public void testDeleteCompany_Exists() {
        // Mock data
        Long companyId = 1L;
        Company existingCompany = new Company();
        existingCompany.setId(companyId);

        // Mock behavior of companyRepository.findById()
        when(companyRepositoryMock.findById(companyId)).thenReturn(Optional.of(existingCompany));

        // Call the method under test
        companyService.deleteCompany(companyId);

        // Verify interaction with companyRepository
        verify(companyRepositoryMock, times(1)).delete(existingCompany);
    }
    @Test
    public void testDeleteCompany_NotFound() {
        // Mock data
        Long companyId = 1L;

        // Mock behavior of companyRepository.findById()
        when(companyRepositoryMock.findById(companyId)).thenReturn(Optional.empty());

        // Call the method under test and assert that it throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> companyService.deleteCompany(companyId));

        // Verify that companyRepository.delete() was not called
        verify(companyRepositoryMock, never()).delete(any());
    }


}
