package com.quizzApp.auth_service.controller;

import com.quizzApp.auth_service.Model.dto.UserLoginDTO;
import com.quizzApp.auth_service.Model.dto.UserRegisterDTO;
import com.quizzApp.auth_service.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("register")
    public ResponseEntity<Boolean> register(@Valid @RequestBody UserRegisterDTO userRegisterDto) {
        userService.registerUser(userRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        String token = userService.loginUser(userLoginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
