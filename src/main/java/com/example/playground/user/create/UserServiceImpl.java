package com.example.playground.user.create;

import com.example.playground.config.SecurityConfig;
import com.example.playground.user.User;
import com.example.playground.user.UserRepository;
import com.example.playground.user.UserRole;
import com.example.playground.user.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    final String FOLDER = "/Users/moazamin/Desktop/twitterImages/";



    public boolean validatePassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public String encryptImage(){
        LocalDateTime now = LocalDateTime.now();
        return String.valueOf(now.getHour());
    }

    @Override
    public void createUser(CreateUserRecord userRecord) {
        Optional<User> us = userRepository.findByUsername(userRecord.username());
        if (us.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A user with that username already exists");

        us = userRepository.findByEmail(userRecord.email());
        if (us.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A user with that email already exists");

        if (!validatePassword(userRecord.password()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must have a minimum of eight characters, at least one letter and one number");


        User user = User.builder()
                        .username(userRecord.username())
                                .firstName(userRecord.firstName())
                                        .lastName(userRecord.lastName())
                                                .email(userRecord.email())
                .password(passwordEncoder.encode(userRecord.password()))
                                                        .build();


        UserRole userRole = UserRole.builder()
                        .user(user)
                                .role(userRecord.roles().get(0))
                                        .build();
        userRepository.save(user);
        userRoleRepository.save(userRole);
    }

    @Override
    public void saveImage(MultipartFile multipartFile) throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong, try again later");


        byte[] bytes = multipartFile.getBytes();
        Path path = Paths.get(FOLDER + encryptImage() + multipartFile.getOriginalFilename() );
        Files.write(path,bytes);

        User user = byUsername.get();

        user.setProfile_image(path.toString());
        userRepository.save(user);
    }

}
