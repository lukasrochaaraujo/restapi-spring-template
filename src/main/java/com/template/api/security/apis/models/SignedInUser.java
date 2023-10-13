package com.template.api.security.apis.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignedInUser {
    private String userId;
    private String username;
    private String accessToken;
    private String refreshToken;
}
