package com.example.playground.user;

import com.example.playground.user.create.CreateUserRecord;
import com.example.playground.user.create.UserController;
import com.example.playground.user.create.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class UserControllerTest {

    @MockBean
    CreateUserRecord userRecord;
    @MockBean
    UserRepository userRepository;
    @MockBean
    UserServiceImpl userService;
    ObjectMapper objectMapper;
    @MockBean
    PasswordEncoder passwordEncoder;

    UserController userController;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        userService = new UserServiceImpl(userRepository,passwordEncoder);
        userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        userRecord = CreateUserRecord.builder().username("moazamin").build();
    }

    @Test
    void createUser() throws Exception {
        User user = User.builder().username("moazamin").build();
        userRepository.save(user);
        when(userRepository.findByUsername("moazamin").isPresent()).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/um/v1/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(userRecord))).andDo(print());
    }
}