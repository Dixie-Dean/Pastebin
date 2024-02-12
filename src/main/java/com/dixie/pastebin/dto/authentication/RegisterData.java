package com.dixie.pastebin.dto.authentication;

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
