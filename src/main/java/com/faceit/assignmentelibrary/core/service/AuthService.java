package com.faceit.assignmentelibrary.core.service;

import com.faceit.assignmentelibrary.core.dto.UserSigninRequestDto;
import com.faceit.assignmentelibrary.core.dto.UserSigninResponseDto;

public interface AuthService {

    public UserSigninResponseDto authenticate(UserSigninRequestDto userSigninRequestDto);
}
