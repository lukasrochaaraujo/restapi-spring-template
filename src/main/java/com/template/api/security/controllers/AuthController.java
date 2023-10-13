package com.template.api.security.controllers;

import com.template.api.security.apis.AuthApi;
import com.template.api.security.apis.models.RefreshToken;
import com.template.api.security.apis.models.SignInRequest;
import com.template.api.security.apis.models.SignUpRequest;
import com.template.api.security.apis.models.SignedInUser;
import com.template.api.security.services.AuthService;
import com.template.api.shared.exceptions.InvalidRefreshTokenException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthService authService, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<SignedInUser> signUp(@Valid SignUpRequest signUpRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.createUser(signUpRequest).get());
    }

    @Override
    public ResponseEntity<SignedInUser> signIn(@Valid SignInRequest signInRequest) {
        var user = authService.findByUsername(signInRequest.username());
        if (passwordEncoder.matches(signInRequest.password(), user.getPassword())) {
            return ResponseEntity.ok(authService.getSignedInUser(user));
        }
        throw new InsufficientAuthenticationException("Unauthorized");
    }

    @Override
    public ResponseEntity<SignedInUser> refreshAccessToken(@Valid RefreshToken refreshToken) {
        return ResponseEntity.ok(authService.getAccessToken(refreshToken)
                .orElseThrow(() -> new InvalidRefreshTokenException("Invalid refresh token")));
    }
}
