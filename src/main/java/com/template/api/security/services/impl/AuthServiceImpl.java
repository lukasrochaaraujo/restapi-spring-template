package com.template.api.security.services.impl;

import com.template.api.security.JwtManager;
import com.template.api.security.apis.models.RefreshToken;
import com.template.api.security.apis.models.SignUpRequest;
import com.template.api.security.apis.models.SignedInUser;
import com.template.api.security.entities.User;
import com.template.api.security.entities.UserToken;
import com.template.api.security.repositories.UserRepository;
import com.template.api.security.repositories.UserTokenRepository;
import com.template.api.security.services.AuthService;
import com.template.api.shared.exceptions.GenericAlreadyExistsException;
import com.template.api.shared.exceptions.InvalidRefreshTokenException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final JwtManager tokenManager;

    public AuthServiceImpl(
        UserRepository userRepository,
        UserTokenRepository userTokenRepository,
        PasswordEncoder bCryptPasswordEncoder,
        JwtManager tokenManager) {
        this.userRepository = userRepository;
        this.userTokenRepository = userTokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenManager = tokenManager;
    }

    @Override
    public User findByUsername(String username) {
        if (Strings.isBlank(username)) {
            throw new UsernameNotFoundException("Invalid user");
        }

        final String uname = username.trim();
        Optional<User> optionalUser = userRepository.findByUsername(uname);
        return optionalUser.orElseThrow(() ->
            new UsernameNotFoundException(String.format("Given user(%) not found.", uname)));
    }

    @Transactional
    @Override
    public Optional<SignedInUser> createUser(SignUpRequest newUser) {
        Integer count = userRepository.findByUsernameOrEmail(newUser.username(), newUser.email());
        if (count > 0) {
            throw new GenericAlreadyExistsException("Use different username and email.");
        }
        var createdUser = userRepository.save(mapToUser(newUser));
        return Optional.of(createSignedInUserWithRefreshToken(createdUser));
    }

    private User mapToUser(SignUpRequest signUpRequest) {
        var user = new User();
        BeanUtils.copyProperties(signUpRequest, user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return user;
    }

    @Transactional
    @Override
    public SignedInUser getSignedInUser(User user) {
        userTokenRepository.deleteByUserId(user.getId());
        return createSignedInUserWithRefreshToken(user);
    }

    @Override
    public Optional<SignedInUser> getAccessToken(RefreshToken refreshToken) {
        return userTokenRepository
                .findByRefreshToken(refreshToken.getRefreshToken())
                .map(ut -> {
                    var userSignedIn = createSignedInUser(ut.getUser());
                    userSignedIn.setRefreshToken(refreshToken.getRefreshToken());
                    return Optional.of(userSignedIn);
                })
                .orElseThrow(() -> new InvalidRefreshTokenException("Invalid token"));
    }

    @Override
    public void removeRefreshToken(RefreshToken refreshToken) {
        userTokenRepository
            .findByRefreshToken(refreshToken.getRefreshToken())
            .ifPresentOrElse(
                userTokenRepository::delete,
                () -> {
                    throw new InvalidRefreshTokenException("Invalid token");
                }
            );
    }

    private SignedInUser createSignedInUserWithRefreshToken(User user) {
        var signedInUser = createSignedInUser(user);
        signedInUser.setRefreshToken(createRefreshToken(user));
        return signedInUser;
    }

    private SignedInUser createSignedInUser(User user) {
        String token = tokenManager.create(
            org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Objects.nonNull(user.getRole()) ? user.getRole().name() : "")
                .build());

        return SignedInUser.builder()
                .username(user.getUsername())
                .accessToken(token)
                .userId(user.getId().toString())
                .build();
    }

    private String createRefreshToken(User user) {
        String token = RandomHolder.randomKey(128);
        var userToken = new UserToken();
        userToken.setRefreshToken(token);
        userToken.setUser(user);
        userTokenRepository.save(userToken);
        return token;
    }

    private static class RandomHolder {
        static final Random random = new SecureRandom();
        public static String randomKey(int length) {
            return String.format("%" + length + "s", new BigInteger(length * 5, random).toString())
                    .replace('\u0020', '0');
        }
    }
}
