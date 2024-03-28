package com.emploiconnect.controller;

import com.emploiconnect.dto.request.AuthenticationRequest;
import com.emploiconnect.dto.request.RegisterRequest;
import com.emploiconnect.dto.response.AuthenticationResponse;
import com.emploiconnect.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @GetMapping
    public List<AuthenticationResponse> getAllUsers() {
        return authenticationService.getAllUsers();
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("test");
    }

    @GetMapping("/candidates")
    public ResponseEntity<List<AuthenticationResponse>> getCandidateUsers() {
        List<AuthenticationResponse> candidateUsers = authenticationService.getCandidateUsers();
        return ResponseEntity.ok(candidateUsers);
    }
    @GetMapping("/recruiters")
    public ResponseEntity<List<AuthenticationResponse>> getRecruiterUsers() {
        List<AuthenticationResponse> recruiterUsers = authenticationService.getRecruiterUsers();
        return ResponseEntity.ok(recruiterUsers);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {

        authenticationService.deleteUser(id);

    }
}
