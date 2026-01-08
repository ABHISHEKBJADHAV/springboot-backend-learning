package com.example.JobAppBackEnd.Service;

import com.example.JobAppBackEnd.DAO.UserRepo;
import com.example.JobAppBackEnd.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder passEncoder;

    public User registerUser(User user)
    {
        user.setPassword(passEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

}
