package com.example.playground.user.create;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping(path = "api/um/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @PostMapping("create")
    public ResponseEntity<Object> createUser(@RequestBody CreateUserRecord userRecord) {
        try {
            userService.createUser(userRecord);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping (path = "upload")
    public ResponseEntity<Object> uploadImage(@RequestParam("file") MultipartFile multipartFile){
        try {
            userService.saveImage(multipartFile);
        } catch (RuntimeException | IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

}
