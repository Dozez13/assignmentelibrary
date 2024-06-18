package com.faceit.assignmentelibrary.web.security.auth.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.faceit.assignmentelibrary.domain.data.access.repository.UserRepository;
import com.faceit.assignmentelibrary.web.security.constant.SecurityConstants;
import com.faceit.assignmentelibrary.web.security.model.UserAuthenticationInfo;
import com.faceit.assignmentelibrary.web.security.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String rawToken = (String) authentication.getCredentials();

        DecodedJWT decodedJWT = jwtUtil.decodeRawJWTToken(rawToken);

        Long id = Long.valueOf(decodedJWT.getSubject());

        boolean isUserExists = userRepository.existsById(id);
        if (!isUserExists) {
            throw new BadCredentialsException("Email or password is incorrect");
        }

        String role = decodedJWT.getClaim(SecurityConstants.ROLE_CLAIM).asString();

        UserAuthenticationInfo authenticationInfo = new UserAuthenticationInfo(id, role);

        return new JwtAuthenticationToken(authenticationInfo);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
