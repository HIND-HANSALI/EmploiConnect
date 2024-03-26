package com.emploiconnect.service;

import com.emploiconnect.dto.response.AuthenticationResponse;
import com.emploiconnect.dto.response.OfferResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
//    Optional<User> getById(Long id);

    public List<AuthenticationResponse> getAllUsers();
//    Role grantRoleToUser(Long userId, Long roleId);
}
