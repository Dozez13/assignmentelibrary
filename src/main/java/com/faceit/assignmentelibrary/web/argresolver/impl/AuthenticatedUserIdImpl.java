package com.faceit.assignmentelibrary.web.argresolver.impl;

import com.faceit.assignmentelibrary.web.argresolver.AuthenticatedUserId;
import com.faceit.assignmentelibrary.web.security.auth.jwt.JwtAuthenticationToken;
import com.faceit.assignmentelibrary.web.security.model.UserAuthenticationInfo;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthenticatedUserIdImpl implements HandlerMethodArgumentResolver {
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticatedUserId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) securityContextHolderStrategy.getContext().getAuthentication();
        UserAuthenticationInfo user = (UserAuthenticationInfo) authentication.getPrincipal();
        return user.getId();
    }
}
