package com.template.api.security.repositories;

import com.template.api.security.entities.UserToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserTokenRepository extends CrudRepository<UserToken, UUID> {
    Optional<UserToken> findByRefreshToken(String refreshToken);
    Optional<UserToken> deleteByUserId(UUID id);
}
