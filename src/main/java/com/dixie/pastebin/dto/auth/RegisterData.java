package com.dixie.pastebin.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterData {
    private String username;
    private String lastname;
    private String email;
    private String password;
}
