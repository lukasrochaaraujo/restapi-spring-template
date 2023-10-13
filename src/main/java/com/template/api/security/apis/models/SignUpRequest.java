package com.template.api.security.apis.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(

    @NotBlank
    String id,

    @NotBlank
    String username,

    @NotBlank
    String password,

    @NotBlank
    String firstName,

    @NotBlank
    String lastName,

    @Email
    String email,

    @NotBlank
    String phone,

    @NotBlank
    String status
) {
}
