package com.emploiconnect.service;

import com.emploiconnect.dto.request.ApplicationRequestDto;
import com.emploiconnect.dto.response.ApplicationResponseDto;
import com.emploiconnect.entity.Application;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ApplicationService {
    List<ApplicationResponseDto> getAllApplications();

    ApplicationResponseDto updateStatusToRejected(Long id);

    ApplicationResponseDto updateStatusToApproved(Long id);
    ApplicationResponseDto createApplication(ApplicationRequestDto applicationRequestDto,Long offerId);
    ApplicationResponseDto createApplicationAdmin(ApplicationRequestDto applicationRequestDto);
    long countRejectedApplications();
    long countApprovedApplications();
}
