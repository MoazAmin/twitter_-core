package com.example.playground.user.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/um/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationEndpoint {

    private final AuthenticationService authenticationService;

    @PostMapping(path = "auth", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<AuthenticationRecord> authenticate(AuthenticationCmd auth) {
        return ResponseEntity.ok(authenticationService.authenticate(auth));
    }

    @GetMapping("admins")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> admins() {
        return ResponseEntity.ok("Admin test endpoint");
    }

    @GetMapping("user")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<String> employees() {
        return ResponseEntity.ok("User test endpoint");
    }

}
