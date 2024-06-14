package com.faceit.assignmentelibrary.web.security.constant;

public final class SecurityConstants {
    public static final String AUTHENTICATION_URL = "/api/v1/user-management/signin";

    public static final String REGISTRATION_URL = "/api/v1/user-management/signup";
    public static final String API_SECURE_URL = "/api/v1/secure/**";

    public static final Integer EXPIRATION_TOKEN_DATE_IN_MIN = 300;

    public static final String ISSUER = "Manuilenko";

    public static final String SECRET = "TEST";
    public static final String ROLE_CLAIM = "role";
    public static final String AUTHENTICATION_HEADER_NAME = "Authorization";

    public static final String HEADER_PREFIX = "Bearer ";

}
