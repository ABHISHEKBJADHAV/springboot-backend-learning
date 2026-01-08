package com.quizzApp.auth_service.Service;

import com.quizzApp.auth_service.DAO.UserRepo;
import com.quizzApp.auth_service.Model.User;
import com.quizzApp.auth_service.Model.UserWrapper;
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
        User user = repo.findByUsername(username).orElse(null);

        if(user==null)
        {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserWrapper(user);
    }
}
