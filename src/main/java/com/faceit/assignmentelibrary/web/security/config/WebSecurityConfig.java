package com.faceit.assignmentelibrary.web.security.config;


import com.faceit.assignmentelibrary.web.security.auth.jwt.JwtAuthenticationConverter;
import com.faceit.assignmentelibrary.web.security.auth.jwt.JwtAuthenticationFilter;
import com.faceit.assignmentelibrary.web.security.constant.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@PropertySource("classpath:security.properties")
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    private AuthenticationProvider jwtProvider;
    @Autowired
    private RequestMatcher skipPathRequestMatcher;


    @Bean
    public SecurityFilterChain appHttpSecurity(HttpSecurity appHttpSecurity) throws Exception {
        appHttpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz.requestMatchers(SecurityConstants.API_SECURE_URL)
                        .authenticated())
                .authenticationManager(authenticationManager())
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return appHttpSecurity.build();
    }


    @Bean
    public AuthenticationConverter jwtAuthenticationConverter() {
        return new JwtAuthenticationConverter();
    }

    @Bean
    public AuthenticationFilter authenticationFilter() {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager(), jwtAuthenticationConverter());
        jwtAuthenticationFilter.setRequestMatcher(skipPathRequestMatcher);
        return jwtAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(jwtProvider);
    }

}
