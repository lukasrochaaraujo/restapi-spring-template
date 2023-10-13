package com.template.api.security.repositories;

import com.template.api.security.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    @Query(
        value = "select count(u.*) from api.\"user\" u where u.username = :username or u.email = :email",
        nativeQuery = true)
    Integer findByUsernameOrEmail(String username, String email);
}
