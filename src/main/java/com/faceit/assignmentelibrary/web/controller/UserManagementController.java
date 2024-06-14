package com.faceit.assignmentelibrary.web.controller;

import com.faceit.assignmentelibrary.core.dto.UserSigninRequestDto;
import com.faceit.assignmentelibrary.core.dto.UserSigninResponseDto;
import com.faceit.assignmentelibrary.core.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-management")
@RequiredArgsConstructor
public class UserManagementController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<UserSigninResponseDto> signIn(@RequestBody UserSigninRequestDto userSigninRequestDto) {

        return new ResponseEntity<>(authService.authenticate(userSigninRequestDto), HttpStatus.OK);
    }


}
