package com.faceit.assignmentelibrary.core.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserSigninResponseDto {

    private String token;
    private String role;
}
