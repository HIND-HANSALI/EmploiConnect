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
@RequestMapping("/api/applications")
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
            return ResponseMessage.ok("Success" ,applications );
        }
    }

}
