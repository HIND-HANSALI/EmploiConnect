package com.emploiconnect.dto.response;
import com.emploiconnect.entity.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String userName;
    private String token;
    private String email;
    private Role role;
}
