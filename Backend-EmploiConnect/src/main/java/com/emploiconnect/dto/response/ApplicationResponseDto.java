package com.emploiconnect.dto.response;

import com.emploiconnect.enums.ApplicationStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponseDto {
    private Long id;

    private ApplicationStatus status;

    @NotNull(message = "Title cannot be null")
    private String title;
    private String cv;
    private String profile;


    private OfferResponseDto offer;
    private AuthenticationResponse user;
}
