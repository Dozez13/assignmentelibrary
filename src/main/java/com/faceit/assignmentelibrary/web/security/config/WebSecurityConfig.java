package com.faceit.assignmentelibrary.web.security.config;


import com.faceit.assignmentelibrary.web.security.constant.SecurityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    @Autowired
    private AuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private AuthenticationFailureHandler authenticationLoginFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler authenticationLoginSuccessfulHandler;
    @Autowired
    private AuthenticationProvider loginProvider;
    @Autowired
    private AuthenticationProvider jwtProvider;

    @Autowired
    private ObjectMapper objectMapper;


    @Bean
    public SecurityFilterChain appHttpSecurity(HttpSecurity appHttpSecurity) throws Exception {
        appHttpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(restAuthenticationEntryPoint))
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz.requestMatchers(SecurityConstants.API_SECURE_URL)
                        .authenticated())
                .with(buildJWTConfigurer(), Customizer.withDefaults());
        return appHttpSecurity.build();
    }

    private JWTConfigurer buildJWTConfigurer() {
        List<AuthenticationProvider> authenticationProviders = Arrays.asList(loginProvider, jwtProvider);
        return new JWTConfigurer(authenticationLoginFailureHandler, authenticationLoginSuccessfulHandler, authenticationProviders, objectMapper);
    }

}
