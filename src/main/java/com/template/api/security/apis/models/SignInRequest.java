package com.template.api.security.apis.models;

import jakarta.validation.constraints.NotBlank;

public record SignInRequest(

    @NotBlank
    String username,

    @NotBlank
    String password
) {
}
