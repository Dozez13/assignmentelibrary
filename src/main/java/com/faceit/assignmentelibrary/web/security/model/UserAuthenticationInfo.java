package com.faceit.assignmentelibrary.web.security.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;


@RequiredArgsConstructor
@Getter
public class UserAuthenticationInfo {

    private final Long id;

    private final String role;

}
