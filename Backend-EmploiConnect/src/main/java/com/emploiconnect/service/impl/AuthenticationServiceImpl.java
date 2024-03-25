package com.emploiconnect.service.impl;

import com.emploiconnect.configuration.JwtService;
import com.emploiconnect.dto.request.AuthenticationRequest;
import com.emploiconnect.dto.request.RegisterRequest;
import com.emploiconnect.dto.response.AuthenticationResponse;
import com.emploiconnect.entity.User;
import com.emploiconnect.repository.UserRepository;
import com.emploiconnect.service.AuthenticationService;

import com.emploiconnect.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;

    @Override
    public List<AuthenticationResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        //if (authorities.contains("VIEW_USERS"))
            return users.stream()
                    .map(this::mapUserToAuthenticationResponse)
                    .collect(Collectors.toList());
        //else return null;


    }
    private AuthenticationResponse mapUserToAuthenticationResponse(User user) {
        AuthenticationResponse response = new AuthenticationResponse();
        response.setFirstName(user.getFirstName());
        response.setFamilyName(user.getFamilyName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        return response;
    }
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .familyName(request.getFamilyName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleService.findDefaultRole().orElse(null))
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken)
                .firstName(request.getFirstName())
                .familyName(request.getFamilyName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .firstName(user.getFirstName())
                .familyName(user.getFamilyName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
    @Override
    public List<AuthenticationResponse> getCandidateUsers() {
        List<User> candidateUsers = userRepository.findByRoleName("CANDIDATE");
       return candidateUsers.stream()
                .map(user -> {
                    AuthenticationResponse response = new AuthenticationResponse();
                    response.setFirstName(user.getFirstName());
                    response.setFamilyName(user.getFamilyName());
                    response.setEmail(user.getEmail());
                    response.setRole(user.getRole());
                    return response;
                })
                .collect(Collectors.toList());
    }
    @Override
    public List<AuthenticationResponse> getRecruiterUsers() {
        List<User> recruiterUsers = userRepository.findByRoleName("RECRUITER");
        return recruiterUsers.stream()
                .map(user -> {
                    AuthenticationResponse response = new AuthenticationResponse();
                    response.setFirstName(user.getFirstName());
                    response.setFamilyName(user.getFamilyName());
                    response.setEmail(user.getEmail());
                    response.setRole(user.getRole());
                    return response;
                })
                .collect(Collectors.toList());
    }
}
