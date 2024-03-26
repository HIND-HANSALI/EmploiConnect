package com.emploiconnect.dto.request;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String familyName;
    private String email;

    private String password;
}
