package com.example.playground.user.auth;

public interface AuthenticationService {

    AuthenticationRecord authenticate(AuthenticationCmd auth);
}
