package com.faceit.assignmentelibrary.web.security.config;


import com.faceit.assignmentelibrary.web.security.auth.jwt.JwtAuthenticationFilter;
import com.faceit.assignmentelibrary.web.security.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@PropertySource("classpath:security.properties")
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AuthenticationSuccessHandler jwtAuthenticationSuccessHandler;
    private final AuthenticationConverter jwtAuthenticationConverter;
    private final AuthenticationProvider jwtAuthenticationProvider;

    private final RequestMatcher skipPathRequestMatcher;


    @Bean
    public SecurityFilterChain appHttpSecurity(HttpSecurity appHttpSecurity) throws Exception {
        AuthenticationManager authenticationManager = buildAuthenticationManager();
        AuthenticationFilter authenticationFilter = buildAuthenticationFilter(authenticationManager);
        appHttpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> {
                    authz.requestMatchers(SecurityConstants.AUTHENTICATION_URL, SecurityConstants.REGISTRATION_URL)
                            .permitAll();
                    authz.requestMatchers(SecurityConstants.API_SECURE_URL)
                            .authenticated();
                })
                .authenticationManager(authenticationManager)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return appHttpSecurity.build();
    }


    public AuthenticationFilter buildAuthenticationFilter(AuthenticationManager authenticationManager) {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtAuthenticationConverter);
        jwtAuthenticationFilter.setRequestMatcher(skipPathRequestMatcher);
        jwtAuthenticationFilter.setSuccessHandler(jwtAuthenticationSuccessHandler);
        return jwtAuthenticationFilter;
    }


    public AuthenticationManager buildAuthenticationManager() {
        return new ProviderManager(jwtAuthenticationProvider);
    }

}
