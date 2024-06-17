package com.faceit.assignmentelibrary.web.controller;

import com.faceit.assignmentelibrary.core.service.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/secure/patrons")
@PreAuthorize("authentication.principal.role == 'PATRON'")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService patronService;

    @GetMapping("/{id}/books")
    public ResponseEntity<?> getAllPatronsBooks(@PathVariable("id") Long id) {

        return new ResponseEntity<>(patronService.getPatronsBooks(id), HttpStatus.OK);
    }

}
