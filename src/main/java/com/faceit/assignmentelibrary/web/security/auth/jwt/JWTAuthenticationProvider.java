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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("jwtProvider")
@RequiredArgsConstructor
public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String rawToken = (String) authentication.getCredentials();

        DecodedJWT decodedJWT = jwtUtil.decodeRawJWTToken(rawToken);

        String email = decodedJWT.getSubject();

        boolean isUserExists = userRepository.existsByEmail(email);
        if (!isUserExists) {
            throw new BadCredentialsException("Email or password is incorrect");
        }

        List<GrantedAuthority> grantedAuthorities = decodedJWT.getClaim(SecurityConstants.SCOPES_CLAIM).asList(String.class).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        String role = decodedJWT.getClaim(SecurityConstants.ROLE_CLAIM).asString();

        UserAuthenticationInfo authenticationInfo = new UserAuthenticationInfo(email, role, grantedAuthorities);

        return new JWTAuthenticationToken(grantedAuthorities, authenticationInfo);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
