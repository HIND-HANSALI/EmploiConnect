package com.emploiconnect.service;

import com.emploiconnect.dto.request.AuthenticationRequest;
import com.emploiconnect.dto.request.RegisterRequest;
import com.emploiconnect.dto.response.AuthenticationResponse;
import com.emploiconnect.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest user);
    List<AuthenticationResponse> getAllUsers();

    List<AuthenticationResponse> getCandidateUsers();
    public List<AuthenticationResponse> getRecruiterUsers();
    AuthenticationResponse authenticate(AuthenticationRequest user);
}
