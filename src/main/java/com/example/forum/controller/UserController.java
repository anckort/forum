package com.example.forum.controller;

import com.example.forum.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public interface UserController {

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user);

    @PostMapping("/auth")
    public String authorisation(@RequestBody String loginData);


}
