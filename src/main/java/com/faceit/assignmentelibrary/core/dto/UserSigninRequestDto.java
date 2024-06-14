package com.faceit.assignmentelibrary.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserSigninRequestDto {
    private String email;
    private String password;
}
