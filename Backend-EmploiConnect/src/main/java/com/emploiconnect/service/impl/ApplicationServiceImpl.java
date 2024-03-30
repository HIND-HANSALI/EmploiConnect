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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import java.nio.file.*;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;
    private FileStorageService fileStorageService;
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
        //application.setCv(applicationRequestDto.getCv());
        application.setProfile(applicationRequestDto.getProfile());
        application.setStatus(ApplicationStatus.PENDING);

        // Store the uploaded CV file
        MultipartFile cvFile = applicationRequestDto.getCv();
        if (cvFile != null) {
            try {
                String fileName = fileStorageService.storeFile(cvFile);
                application.setCv(fileName);
            } catch (IOException ex) {
                throw new RuntimeException("Could not store CV file. Please try again!", ex);
            }
        }

        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id: " + applicationRequestDto.getOfferId()));
        application.setOffer(offer);

        //Retrieve the Authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract the email or username of the authenticated user
        String userEmail = authentication.getName();

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found for email: " + userEmail));

        application.setUser(user);

        // Check if the user has already applied for the offer
        boolean isUserApplied = applicationRepository.existsByUserIdAndOfferId(user.getId(), offerId);

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
    public ApplicationResponseDto createApplicationNew(ApplicationRequestDto applicationRequestDto,
                                                    MultipartFile file, Long offerId) {
        // Validate file and parameters
        if (file == null || file.isEmpty() || StringUtils.isEmpty(applicationRequestDto.getTitle()) ||
                StringUtils.isEmpty(applicationRequestDto.getProfile())) {
            throw new IllegalArgumentException("File, title, and profile cannot be empty");
        }

        // Store the file
        String fileName = storeFile(file);

        // Create your application entity and save it to the database
        Application application = new Application();
        application.setTitle(applicationRequestDto.getTitle());
        application.setProfile(applicationRequestDto.getProfile());
        application.setCv(fileName);
        application.setStatus(ApplicationStatus.PENDING);
        // Other application entity properties initialization

        // Retrieve and set the offer
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id: " + offerId));
        application.setOffer(offer);

        // Store the user
        //Retrieve the Authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract the email or username of the authenticated user
        String userEmail = authentication.getName();

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found for email: " + userEmail));

        application.setUser(user);

        // Check if the user has already applied for the offer
        boolean isUserApplied = applicationRepository.existsByUserIdAndOfferId(user.getId(), offerId);
        if (isUserApplied) {
            throw new OperationException("Candidate is already registered for the offer");
        }

        // Save the application entity to the database
        Application savedApplication = applicationRepository.save(application);

        // Map the saved application entity to ApplicationResponseDto
        ApplicationResponseDto responseDto =modelMapper.map(savedApplication, ApplicationResponseDto.class);

        return responseDto;
    }
    private static final String UPLOAD_DIR = "src/main/java/com/emploiconnect/uploads";

    private String storeFile(MultipartFile file) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Failed to store file: " + ex.getMessage(), ex);
        }
    }
    /*private static final String BUCKET_NAME = "emploi-connect-4895a.appspot.com"; // Replace with your Firebase Storage bucket name

    public String storeFile(MultipartFile file) {
        try {
            // Generate a random file name to prevent collisions
            String fileName = UUID.randomUUID().toString() + getFileExtension(file.getOriginalFilename());

            // Initialize the Firebase Storage client
            Storage storage = StorageOptions.getDefaultInstance().getService();

            // Create a BlobId and BlobInfo
            BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();

            // Upload the file to Firebase Storage
            storage.create(blobInfo, file.getBytes());

            // Return the file name (or file path if needed)
            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Failed to store file: " + ex.getMessage(), ex);
        }
    }*/

    // Helper method to get file extension
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex);
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
        //application.setCv(applicationRequestDto.getCv());
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
