package com.template.api.shared.exceptions;

import java.io.Serial;

public class GenericAlreadyExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public GenericAlreadyExistsException(final String message) {
        super(message);
    }
}
