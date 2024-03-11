package com.emploiconnect.service.impl;

import com.emploiconnect.dto.request.ApplicationRequestDto;
import com.emploiconnect.dto.response.ApplicationResponseDto;
import com.emploiconnect.dto.response.OfferResponseDto;
import com.emploiconnect.entity.Application;
import com.emploiconnect.entity.Offer;
import com.emploiconnect.entity.User;
import com.emploiconnect.enums.ApplicationStatus;
import com.emploiconnect.handler.exception.ResourceNotFoundException;
import com.emploiconnect.repository.ApplicationRepository;
import com.emploiconnect.repository.OfferRepository;
import com.emploiconnect.repository.UserRepository;
import com.emploiconnect.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;
    @Override
    public List<ApplicationResponseDto> getAllApplications() {
        List<Application> applications=applicationRepository.findAll();
        return applications.stream()
                .map(application->modelMapper.map(application, ApplicationResponseDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public ApplicationResponseDto createApplication(ApplicationRequestDto applicationRequestDto) {
        // Create a new Application entity
        Application application = new Application();
        application.setTitle(applicationRequestDto.getTitle());
        application.setCv(applicationRequestDto.getCv());
        application.setProfile(applicationRequestDto.getProfile());
        application.setStatus(ApplicationStatus.PENDING);

        // Retrieve the offer from the database based on its ID
        Offer offer = offerRepository.findById(applicationRequestDto.getOfferId())
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id: " + applicationRequestDto.getOfferId()));
        application.setOffer(offer);

        // Retrieve the user from the database based on its ID
        User user = userRepository.findById(applicationRequestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + applicationRequestDto.getUserId()));
        application.setUser(user);

        // Save the application to the database
        Application savedApplication = applicationRepository.save(application);

        // Map the saved application to an ApplicationResponseDto
        ApplicationResponseDto applicationResponseDto = modelMapper.map(savedApplication, ApplicationResponseDto.class);

        // Return the ApplicationResponseDto
        return applicationResponseDto;
    }

}
