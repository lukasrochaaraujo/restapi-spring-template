package com.template.api.security.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER(Const.USER),
    ADMIN(Const.ADMIN),
    CSR(Const.CSR);

    private String authority;

    Role(String authority) {
        this.authority = authority;
    }

    @JsonCreator
    public static Role fromAuthority(String authority) {
        for (Role b : Role.values()) {
            if (b.authority.equals(authority)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + authority + "'");
    }

    @Override
    public String toString() {
        return String.valueOf(authority);
    }

    @Override
    @JsonValue
    public String getAuthority() {
        return authority;
    }

    public class Const {
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String USER = "ROLE_USER";
        public static final String CSR = "ROLE_CSR";
    }
}
