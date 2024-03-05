package com.dixie.pastebin.security.authentication.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInData {
    private String email;
    private String password;
}
