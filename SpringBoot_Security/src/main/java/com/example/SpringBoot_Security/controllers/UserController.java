package com.example.SpringBoot_Security.controllers;

import com.example.SpringBoot_Security.models.User;
import com.example.SpringBoot_Security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("register")
    public User registerUser(@RequestBody User user)
    {
        return service.registerUser(user);
    }


}
