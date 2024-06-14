package com.faceit.assignmentelibrary.web.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.faceit.assignmentelibrary.domain.data.access.entity.User;
import com.faceit.assignmentelibrary.web.security.constant.SecurityConstants;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JWTUtil {


    public String generateJWTToken(User authenticationInfo) {
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET.getBytes());
        return JWT.create()
                .withSubject(authenticationInfo.getEmail())
                .withClaim(SecurityConstants.ROLE_CLAIM, authenticationInfo.getUserRole().name())
                .withIssuer(SecurityConstants.ISSUER)
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(Date.from(Instant.now().plus(SecurityConstants.EXPIRATION_TOKEN_DATE_IN_MIN, ChronoUnit.MINUTES)))
                .sign(algorithm);
    }

    public DecodedJWT decodeRawJWTToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET.getBytes());
        return JWT.require(algorithm)
                .withIssuer(SecurityConstants.ISSUER)
                .build()
                .verify(token);

    }
}
