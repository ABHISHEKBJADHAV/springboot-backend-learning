package com.example.SpringBoot_Security.services;

import com.example.SpringBoot_Security.dao.UserRepo;
import com.example.SpringBoot_Security.models.User;
import com.example.SpringBoot_Security.models.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUserName(username).orElse(null);

        if(user==null)
        {
            System.out.println("User 404");
            throw new UsernameNotFoundException("User 404");
        }

        return new UserWrapper(user);
    }
}
