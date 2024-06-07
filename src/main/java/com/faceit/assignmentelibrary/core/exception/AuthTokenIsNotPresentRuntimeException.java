package com.faceit.assignmentelibrary.core.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthTokenIsNotPresentRuntimeException extends AuthenticationException {
    public AuthTokenIsNotPresentRuntimeException(String message) {
        super(message);
    }
}
