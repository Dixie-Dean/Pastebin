package com.dixie.pastebin.security.authentication.model.dto;

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
