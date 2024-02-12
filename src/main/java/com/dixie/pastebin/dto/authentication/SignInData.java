package com.dixie.pastebin.dto.authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInData {
    private String email;
    private String password;
}
