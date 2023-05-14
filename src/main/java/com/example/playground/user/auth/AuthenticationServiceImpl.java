package com.example.playground.user.auth;


import com.example.playground.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    final AuthenticationManager authenticationManager;
    final JwtUtils jwtUtils;

    @Override
    public AuthenticationRecord authenticate(final AuthenticationCmd auth) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            auth.getEmail(),
            auth.getPassword()));
        return new AuthenticationRecord(jwtUtils.createToken(auth.getEmail()));
    }
}
