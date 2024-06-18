package com.faceit.assignmentelibrary.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;

public class BookNotFoundException extends ErrorResponseException {
    private static final String ERROR_CODE_URI = "http://localhost:8080/error-code";

    public BookNotFoundException(String errorCode, String detail, Long id) {
        super(HttpStatus.NOT_FOUND);
        setTitle("User with ID is not found: " + id);
        setDetail(detail);
        setTypeFromErrorCode(errorCode);
    }

    private void setTypeFromErrorCode(String errorCode) {
        URI type = new DefaultUriBuilderFactory()
                .uriString(ERROR_CODE_URI)
                .pathSegment(errorCode)
                .build();
        setType(type);
    }

}
