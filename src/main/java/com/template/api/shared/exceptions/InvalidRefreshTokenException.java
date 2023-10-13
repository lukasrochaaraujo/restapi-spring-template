package com.template.api.shared.exceptions;

import java.io.Serial;

public class InvalidRefreshTokenException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public InvalidRefreshTokenException(final String message) {
        super(message);
    }
}
