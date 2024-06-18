package com.faceit.assignmentelibrary.core.service;

import com.faceit.assignmentelibrary.core.dto.UserSigninRequestDto;
import com.faceit.assignmentelibrary.core.dto.UserSigninResponseDto;
import com.faceit.assignmentelibrary.core.dto.PatronSignupRequestDto;

public interface AuthService {

    UserSigninResponseDto authenticate(UserSigninRequestDto userSigninRequestDto);
    void signUp(PatronSignupRequestDto patronSignupRequestDto);
}
