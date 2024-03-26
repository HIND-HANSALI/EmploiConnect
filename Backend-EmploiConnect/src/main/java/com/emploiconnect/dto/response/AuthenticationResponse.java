package com.emploiconnect.dto.response;
import com.emploiconnect.entity.Company;
import com.emploiconnect.entity.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String firstName;
    private String familyName;
    private String token;
    private String email;
    private Role role;
    private Company company;
}
