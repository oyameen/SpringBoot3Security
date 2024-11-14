package com.oyameen.SpringBootSecurity3.service;

import com.oyameen.SpringBootSecurity3.model.User;
import com.oyameen.SpringBootSecurity3.model.UserPrincipal;
import com.oyameen.SpringBootSecurity3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findUserByUserEmail(email);
        if (user == null)
        {
            System.out.println("User not found in db.");
            throw new UsernameNotFoundException("User not found exception raised.");
        }

        return new UserPrincipal(user);
    }
}
