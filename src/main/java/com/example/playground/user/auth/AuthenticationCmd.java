package com.example.playground.user.auth;

import lombok.Data;

@Data
public class AuthenticationCmd {

    private String email;
    private String password;
}
