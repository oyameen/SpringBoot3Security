package com.oyameen.SpringBootSecurity3.dbUtils;

import com.oyameen.SpringBootSecurity3.model.User;
import com.oyameen.SpringBootSecurity3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) {
        User user = new User("user", "user@xyz.com", passwordEncoder.encode("user123"), "USER", "");
        User admin = new User("admin", "admin@xyz.com", passwordEncoder.encode("admin123"), "ADMIN", "ACCESS_TEST1,ACCESS_TEST2");
        User manager = new User("manager", "manager@xyz.com", passwordEncoder.encode("manager123"), "MANAGER", "ACCESS_TEST1");

        List<User> users = Arrays.asList(user, admin, manager);

        userRepository.saveAll(users);
    }
}