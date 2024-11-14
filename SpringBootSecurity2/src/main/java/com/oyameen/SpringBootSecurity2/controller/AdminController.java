package com.oyameen.SpringBootSecurity2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    @GetMapping("index")
    public String index(){
        return "Welcome to admin page.";
    }
}
