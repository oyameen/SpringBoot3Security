package com.oyameen.SpringBootSecurity3.service;

import com.oyameen.SpringBootSecurity3.model.LoginDto;
import com.oyameen.SpringBootSecurity3.model.User;
import com.oyameen.SpringBootSecurity3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    public User register(LoginDto loginDto) {
        User user = new User(loginDto.getUserName(), loginDto.getUserEmail(),
                passwordEncoder.encode(loginDto.getPassword()), "USER", "");
        userRepository.save(user);
        return user;
    }

    public String verify(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUserEmail(), loginDto.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(loginDto.getUserEmail());
        } else {
            return "verifying JWT token failed.";
        }
    }
}
