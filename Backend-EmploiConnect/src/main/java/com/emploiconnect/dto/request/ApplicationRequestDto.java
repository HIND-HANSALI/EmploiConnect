package com.emploiconnect.dto.request;

import com.emploiconnect.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequestDto {
    @NotNull
    private String title;
    @NotNull
    private String cv;
    @NotNull
    private String profile;
    @NotNull
    private ApplicationStatus status;

    private Long offerId;

    @NotNull
    private Long userId;
}
