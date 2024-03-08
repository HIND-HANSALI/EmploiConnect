package com.emploiconnect.dto.request;

import jakarta.validation.constraints.NotNull;

public class ApplicationRequest {
    @NotNull
    private String title;
    @NotNull
    private String cv;
    @NotNull
    private String profile;
    @NotNull
    private String status;
}
