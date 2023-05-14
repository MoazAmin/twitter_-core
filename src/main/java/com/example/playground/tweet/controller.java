package com.example.playground.tweet;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class controller {

    @RequestMapping("/")
    public String sayHello(){
        return "Hello mofo";
    }
}
