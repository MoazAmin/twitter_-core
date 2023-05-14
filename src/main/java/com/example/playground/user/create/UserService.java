package com.example.playground.user.create;


import com.example.playground.user.User;
import com.example.playground.user.UserRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    void createUser(CreateUserRecord userRecord);
    void saveImage(MultipartFile multipartFile) throws IOException;
}
