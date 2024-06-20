package com.faceit.assignmentelibrary.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;

public class BookNotFoundException extends ErrorResponseException {
    private static final String ERROR_CODE_URI = "http://localhost:8080/error-code";
    private static final String ERROR_CODE = "book-not-found";
    private static final URI TYPE = new DefaultUriBuilderFactory()
            .uriString(ERROR_CODE_URI)
            .pathSegment(ERROR_CODE)
            .build();


    public BookNotFoundException(String title) {
        super(HttpStatus.NOT_FOUND);
        setTitle(title);
        setType(TYPE);
    }


}
