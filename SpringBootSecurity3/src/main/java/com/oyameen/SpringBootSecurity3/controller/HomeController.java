package com.oyameen.SpringBootSecurity3.controller;

import com.oyameen.SpringBootSecurity3.model.LoginDto;
import com.oyameen.SpringBootSecurity3.model.User;
import com.oyameen.SpringBootSecurity3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public RedirectView redirectView() {
        return new RedirectView("/index");
    }

    @GetMapping("index")
    public String index() {
        return "Welcome to home page.";
    }

    @PostMapping("register")
    public User register(@RequestBody LoginDto loginDto) {
        return userService.register(loginDto);

    }

    @PostMapping("login")
    public String login(@RequestBody LoginDto loginDto) {
        return userService.verify(loginDto);
    }

}
