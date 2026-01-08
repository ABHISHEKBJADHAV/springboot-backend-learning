package com.example.SpringBoot_Security.services;

import com.example.SpringBoot_Security.dao.UserRepo;
import com.example.SpringBoot_Security.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

//    private BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    private PasswordEncoder passEncoder;

    public User registerUser(User user)
    {
        user.setPassword(passEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

}
