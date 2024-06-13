package com.faceit.assignmentelibrary.web.security.auth.jwt;

import com.faceit.assignmentelibrary.core.exception.AuthTokenIsNotPresentRuntimeException;
import com.faceit.assignmentelibrary.web.security.constant.SecurityConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import java.util.Optional;

public class JwtAuthenticationConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {
        String rawTokenWithPrefix = Optional.ofNullable(request.getHeader(SecurityConstants.AUTHENTICATION_HEADER_NAME)).orElseThrow(() -> new AuthTokenIsNotPresentRuntimeException("Authentication token is not present"));
        String rawTokenWithoutPrefix = rawTokenWithPrefix.substring(SecurityConstants.HEADER_PREFIX.length());
        return new JWTAuthenticationToken(rawTokenWithoutPrefix);
    }
}
