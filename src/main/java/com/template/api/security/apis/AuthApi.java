package com.template.api.security.apis;

import com.template.api.security.apis.models.RefreshToken;
import com.template.api.security.apis.models.SignInRequest;
import com.template.api.security.apis.models.SignUpRequest;
import com.template.api.security.apis.models.SignedInUser;
import com.template.api.shared.exceptions.global.ApiError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "Auth API")
public interface AuthApi {

    @ApiOperation("Creates a new user and generates the JWT (access token) and refresh token")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Ok", response = SignedInUser.class),
        @ApiResponse(code = 500, message = "An unxpected error ocurred", response = ApiError.class)
    })
    @PostMapping(value = "/api/v1/auth/signup")
    ResponseEntity<SignedInUser> signUp(@RequestBody SignUpRequest signUpRequest);

    @ApiOperation("SignIn the user and generates the JWT (access token) and refresh token")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Ok", response = SignedInUser.class),
        @ApiResponse(code = 500, message = "An unxpected error ocurred", response = ApiError.class)
    })
    @PostMapping(value = "/api/v1/auth/token")
    ResponseEntity<SignedInUser> signIn(@RequestBody SignInRequest signInRequest);

    @ApiOperation("Provides new JWT based on valid refresh token")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = SignedInUser.class),
            @ApiResponse(code = 500, message = "An unxpected error ocurred", response = ApiError.class)
    })
    @PostMapping(value = "/api/v1/auth/token/refresh")
    ResponseEntity<SignedInUser> refreshAccessToken(@RequestBody RefreshToken refreshToken);
}
