package com.emploiconnect.service.impl;

import com.emploiconnect.dto.request.ApplicationRequestDto;
import com.emploiconnect.dto.response.ApplicationResponseDto;
import com.emploiconnect.dto.response.OfferResponseDto;
import com.emploiconnect.entity.Application;
import com.emploiconnect.entity.Offer;
import com.emploiconnect.entity.User;
import com.emploiconnect.enums.ApplicationStatus;
import com.emploiconnect.handler.exception.OperationException;
import com.emploiconnect.handler.exception.ResourceNotFoundException;
import com.emploiconnect.repository.ApplicationRepository;
import com.emploiconnect.repository.OfferRepository;
import com.emploiconnect.repository.UserRepository;
import com.emploiconnect.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ApplicationResponseDto createApplication(ApplicationRequestDto applicationRequestDto,Long offerId) {
        // Create a new Application entity
        Application application = new Application();
        application.setTitle(applicationRequestDto.getTitle());
        application.setCv(applicationRequestDto.getCv());
        application.setProfile(applicationRequestDto.getProfile());
        application.setStatus(ApplicationStatus.PENDING);

        // Retrieve the offer from the database based on its ID
        /*Offer offer = offerRepository.findById(applicationRequestDto.getOfferId())
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id: " + applicationRequestDto.getOfferId()));
        application.setOffer(offer);*/

        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id: " + applicationRequestDto.getOfferId()));
        application.setOffer(offer);

        //Retrieve the Authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract the email or username of the authenticated user
        String userEmail = authentication.getName(); // Assuming email is used for authentication
        String message = "Competitions for current user: " + userEmail;
        // Step 3: Query the database to find the member by email
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found for email: " + userEmail));

        // Retrieve the user from the database based on its ID
        /* User user = userRepository.findById(applicationRequestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + applicationRequestDto.getUserId()));
                */

        application.setUser(user);

        // Check if the user has already applied for the offer
        boolean isUserApplied = applicationRepository.existsByUserIdAndOfferId(
                applicationRequestDto.getUserId(), applicationRequestDto.getOfferId());

        if (isUserApplied) {
            throw new OperationException("Candidat is already registered for the offer");
        }

        // Save the application to the database
        Application savedApplication = applicationRepository.save(application);

        // Map the saved application to an ApplicationResponseDto
        ApplicationResponseDto applicationResponseDto = modelMapper.map(savedApplication, ApplicationResponseDto.class);

        // Return the ApplicationResponseDto
        return applicationResponseDto;
    }
    @Override
    public ApplicationResponseDto updateStatusToRejected(Long id){

        Application existingApplication = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + id));

        // Update the status of the existing application to "REJECTED"
        existingApplication.setStatus(ApplicationStatus.REJECTED);

        Application updatedApplication = applicationRepository.save(existingApplication);

        // Map the updated application to an ApplicationResponseDto
        ApplicationResponseDto applicationResponseDto = modelMapper.map(updatedApplication, ApplicationResponseDto.class);

        // Return the ApplicationResponseDto
        return applicationResponseDto;
    }
    @Override
    public ApplicationResponseDto updateStatusToApproved(Long id){

        Application existingApplication = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + id));

        existingApplication.setStatus(ApplicationStatus.APPROVED);
        Application updatedApplication = applicationRepository.save(existingApplication);
        ApplicationResponseDto applicationResponseDto = modelMapper.map(updatedApplication, ApplicationResponseDto.class);
        return applicationResponseDto;
    }

    @Override
    public long countRejectedApplications() {
        return applicationRepository.countByStatus(ApplicationStatus.REJECTED);
    }
    @Override
    public long countApprovedApplications() {
        return applicationRepository.countByStatus(ApplicationStatus.APPROVED);
    }

    @Override
    public ApplicationResponseDto createApplicationAdmin(ApplicationRequestDto applicationRequestDto) {
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

        // Check if the user has already applied for the offer
        boolean isUserApplied = applicationRepository.existsByUserIdAndOfferId(
                applicationRequestDto.getUserId(), applicationRequestDto.getOfferId());

        if (isUserApplied) {
            throw new OperationException("Candidat is already registered for the offer");
        }

        // Save the application to the database
        Application savedApplication = applicationRepository.save(application);

        // Map the saved application to an ApplicationResponseDto
        ApplicationResponseDto applicationResponseDto = modelMapper.map(savedApplication, ApplicationResponseDto.class);

        // Return the ApplicationResponseDto
        return applicationResponseDto;
    }
}
