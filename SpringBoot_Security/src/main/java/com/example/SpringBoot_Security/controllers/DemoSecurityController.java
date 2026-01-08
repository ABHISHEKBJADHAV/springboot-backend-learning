package com.example.SpringBoot_Security.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoSecurityController {

    @GetMapping("home")
    public String goToHome(HttpServletRequest httpServletRequest) {
        return "Home loaded..."+httpServletRequest.getSession().getId();
    }

    @GetMapping("about")
    public String about(HttpServletRequest request) {
        return "Demo Security"+request.getSession().getId();
    }

}
