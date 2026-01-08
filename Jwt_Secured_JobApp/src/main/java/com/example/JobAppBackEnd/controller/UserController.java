package com.example.JobAppBackEnd.controller;

import com.example.JobAppBackEnd.Model.User;
import com.example.JobAppBackEnd.Service.JwtService;
import com.example.JobAppBackEnd.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("register")
    public User registerUser(@RequestBody User user)
    {
        return service.registerUser(user);
    }

    @PostMapping("login")
    public ResponseEntity<String> loginUser(@RequestBody User user)
    {
        try
        {
            authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
            return ResponseEntity.ok(jwtService.generateToken(user.getUserName()));
        }
        catch(AuthenticationException ex)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error in authentication: "+ex.getMessage());
        }
    }



}
