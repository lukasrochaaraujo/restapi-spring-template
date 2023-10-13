package com.template.api.security.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
@Table(name = "user_token")
public class UserToken {
    @Id
    @GeneratedValue
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @NotNull(message = "Refresh token is required.")
    @Basic(optional = false)
    @Column(name = "refresh_token")
    private String refreshToken;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public UUID getId() {
        return id;
    }

    public UserToken setId(UUID id, User user, String refreshToken) {
        this.id = id;
        this.refreshToken = refreshToken;
        this.user = user;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public UserToken setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserToken setUser(User user) {
        this.user = user;
        return this;
    }
}
