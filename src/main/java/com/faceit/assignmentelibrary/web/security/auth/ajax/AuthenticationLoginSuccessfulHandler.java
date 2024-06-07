package com.faceit.assignmentelibrary.web.security.auth.ajax;


import com.faceit.assignmentelibrary.web.security.model.UserAuthenticationInfo;
import com.faceit.assignmentelibrary.web.security.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthenticationLoginSuccessfulHandler implements AuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserAuthenticationInfo authenticationInfo = (UserAuthenticationInfo) authentication.getPrincipal();
        String jwt = jwtUtil.generateJWTToken(authenticationInfo);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("ROLE", authenticationInfo.getRole());
        tokenMap.put("token", jwt);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.OK.value());
        objectMapper.writeValue(response.getWriter(), tokenMap);
        clearAuthenticationAttributes(request);
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (Objects.isNull(session)) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
