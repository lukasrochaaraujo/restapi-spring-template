package com.template.api.security.services;

import com.template.api.security.apis.models.RefreshToken;
import com.template.api.security.apis.models.SignUpRequest;
import com.template.api.security.apis.models.SignedInUser;
import com.template.api.security.entities.User;

import java.util.Optional;

public interface AuthService {
    User findByUsername(String username);
    Optional<SignedInUser> createUser(SignUpRequest user);
    SignedInUser getSignedInUser(User user);
    Optional<SignedInUser> getAccessToken(RefreshToken refreshToken);
    void removeRefreshToken(RefreshToken refreshToken);
}
