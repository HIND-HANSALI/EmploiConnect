package com.emploiconnect.controller;

import com.emploiconnect.dto.request.ApplicationRequestDto;
import com.emploiconnect.dto.response.ApplicationResponseDto;
import com.emploiconnect.handler.response.ResponseMessage;
import com.emploiconnect.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    @GetMapping
    public ResponseEntity<List<ApplicationResponseDto>> getAllApplications(){
        List<ApplicationResponseDto> applications=applicationService.getAllApplications();
        if(applications.isEmpty()) {
            return ResponseMessage.notFound("Application not found");
        }else {
            return new ResponseEntity<>(applications, HttpStatus.OK);
            //return ResponseMessage.ok("Success" ,applications );
        }
    }
    @PostMapping("/{offerId}")
    public ResponseEntity<ApplicationResponseDto> createApplication(@RequestBody ApplicationRequestDto applicationRequestDto,@PathVariable Long offerId) {
        ApplicationResponseDto createdApplication = applicationService.createApplication(applicationRequestDto,offerId);
            //return new ResponseEntity<>(createdApplication, HttpStatus.CREATED);
        if(createdApplication ==null){
            return ResponseMessage.badRequest("Application not created");
        }else{
            return ResponseMessage.created("Application created successfully" ,createdApplication);
        }
    }

    @GetMapping("/reject/count")
    public ResponseEntity<Long> countRejectedApplications() {
        long count = applicationService.countRejectedApplications();
        return ResponseEntity.ok(count);
    }
    @GetMapping("/approve/count")
    public ResponseEntity<Long> countApprovedApplications() {
        long count = applicationService.countApprovedApplications();
        return ResponseEntity.ok(count);
    }
    @PutMapping("/{id}/reject")
    public ResponseEntity<ApplicationResponseDto> updateStatusToRejected(@PathVariable Long id) {
        ApplicationResponseDto updatedApplication = applicationService.updateStatusToRejected(id);
        if (updatedApplication != null) {
            return ResponseMessage.ok("rejected Success" ,updatedApplication );
        } else {
            return ResponseMessage.notFound("Application not found");
        }
    }
    @PutMapping("/{id}/approve")
    public ResponseEntity<ApplicationResponseDto> updateStatusToApproved(@PathVariable Long id) {
        ApplicationResponseDto updatedApplication = applicationService.updateStatusToApproved(id);
        if (updatedApplication != null) {
            return ResponseMessage.ok("approved Success" ,updatedApplication );
        } else {
            return ResponseMessage.notFound("Application not found");
        }
    }
    @PostMapping("/admin")
    public ResponseEntity<ApplicationResponseDto> createApplicationAdmin(@RequestBody ApplicationRequestDto applicationRequestDto) {
        ApplicationResponseDto createdApplication = applicationService.createApplicationAdmin(applicationRequestDto);
        //return new ResponseEntity<>(createdApplication, HttpStatus.CREATED);
        if(createdApplication ==null){
            return ResponseMessage.badRequest("Application not created");
        }else{
            return ResponseMessage.created("Application created successfully" ,createdApplication);
        }
    }
}
