package com.faceit.assignmentelibrary.web.security.auth.jwt;


import com.faceit.assignmentelibrary.web.security.model.UserAuthenticationInfo;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private String token;

    private UserAuthenticationInfo authenticationInfo;

    public JwtAuthenticationToken(UserAuthenticationInfo authenticationInfo) {
        super(null);
        this.eraseCredentials();
        this.authenticationInfo = authenticationInfo;
        this.setAuthenticated(true);
    }

    public JwtAuthenticationToken(String token) {
        super(null);
        this.token = token;
        this.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.token = null;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    @Override
    public Object getPrincipal() {
        return this.authenticationInfo;
    }
}
