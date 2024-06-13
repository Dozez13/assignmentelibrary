package com.faceit.assignmentelibrary.web.controller;

import com.faceit.assignmentelibrary.core.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserManagementController {

    private final AuthService authService;


}
