package com.template.api.security.apis.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshToken {
    @NotBlank
    private String refreshToken;
    private String accessToken;
    private String username;
    private String userId;
}
