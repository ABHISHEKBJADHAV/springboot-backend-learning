package com.quizzApp.auth_service.Service;

import com.quizzApp.auth_service.DAO.RoleRepo;
import com.quizzApp.auth_service.DAO.UserRepo;
import com.quizzApp.auth_service.Model.Role;
import com.quizzApp.auth_service.Model.User;
import com.quizzApp.auth_service.Model.dto.UserLoginDTO;
import com.quizzApp.auth_service.Model.dto.UserRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public User registerUser(UserRegisterDTO userRegisterDto)
    {
        if (userRepo.existsByUsername(userRegisterDto.username())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(userRegisterDto.username());
        user.setEmail(userRegisterDto.email());
        user.setPassword(passEncoder.encode(userRegisterDto.password()));

        Role defaultRole = roleRepo.findByName("ROLE_USER").
                orElseThrow(() -> new RuntimeException("Default role not found"));
        user.getRoles().add(defaultRole);
        return userRepo.save(user);
    }

    public String loginUser(@Valid UserLoginDTO userLoginDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.username(), userLoginDTO.password())
            );
            return jwtService.generateToken(userLoginDTO.username());
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
