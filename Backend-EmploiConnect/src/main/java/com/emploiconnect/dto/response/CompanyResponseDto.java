package com.emploiconnect.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponseDto {
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Sector is required")
    private String sector;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Start date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Foundation date must be in the past")
    private LocalDate foundationDate;

    @NotBlank(message = "Specializations is required")
    private String specializations;

    @NotBlank(message = "Description is required")
    private String description;
}
