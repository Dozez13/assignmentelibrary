package com.faceit.assignmentelibrary.web.security.auth.ajax;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserLoginDto {

    private String email;

    private String password;


}
