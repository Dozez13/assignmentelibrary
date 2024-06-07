package com.faceit.assignmentelibrary.core.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthMethodIsNotSupportedRuntimeException extends AuthenticationException {
    public AuthMethodIsNotSupportedRuntimeException(String message) {
        super(message);
    }
}
