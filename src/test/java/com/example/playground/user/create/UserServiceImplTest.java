package com.example.playground.user.create;

import com.example.playground.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    UserServiceImpl userService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp(){
        userService = new UserServiceImpl(userRepository,passwordEncoder);
    }

    @Test
    void validatePassword() {
        boolean a = userService.validatePassword("12345678a");

        assert a;
    }
}