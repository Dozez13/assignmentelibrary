package com.faceit.assignmentelibrary.core.service.impl;

import com.faceit.assignmentelibrary.core.dto.PatronSignupRequestDto;
import com.faceit.assignmentelibrary.core.dto.UserSigninRequestDto;
import com.faceit.assignmentelibrary.core.dto.UserSigninResponseDto;
import com.faceit.assignmentelibrary.core.mapper.PatronMapper;
import com.faceit.assignmentelibrary.core.service.AuthService;
import com.faceit.assignmentelibrary.domain.data.access.entity.Patron;
import com.faceit.assignmentelibrary.domain.data.access.entity.User;
import com.faceit.assignmentelibrary.domain.data.access.repository.PatronRepository;
import com.faceit.assignmentelibrary.domain.data.access.repository.UserRepository;
import com.faceit.assignmentelibrary.web.security.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PatronRepository patronRepository;
    private final PatronMapper patronMapper;

    @Override
    public UserSigninResponseDto authenticate(UserSigninRequestDto userSigninRequestDto) {
        User userToAuthenticate = userRepository.findByEmail(userSigninRequestDto.getEmail()).orElseThrow(() -> new BadCredentialsException("Email or password is incorrect"));
        if (!passwordEncoder.matches(userSigninRequestDto.getPassword(), userToAuthenticate.getPassword())) {
            throw new BadCredentialsException("Email or password is incorrect");
        }
        UserSigninResponseDto userSigninResponseDto = new UserSigninResponseDto();
        userSigninResponseDto.setRole(userToAuthenticate.getUserRole().name());
        userSigninResponseDto.setToken(jwtUtil.generateJWTToken(userToAuthenticate));
        return userSigninResponseDto;
    }

    @Override
    public void signUp(PatronSignupRequestDto patronSignupRequestDto) {
        Patron patron = patronMapper.toPatronFromUserSignupRequestDto(patronSignupRequestDto);
        patronRepository.save(patron);
    }
}
