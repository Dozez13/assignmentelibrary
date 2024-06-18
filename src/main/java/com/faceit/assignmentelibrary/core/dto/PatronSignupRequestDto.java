package com.faceit.assignmentelibrary.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PatronSignupRequestDto {

    private String email;

    private String firstName;

    private String lastName;

    private String password;
}