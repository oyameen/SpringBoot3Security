package com.oyameen.SpringBootSecurity3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management")
public class ManagementController {

    @GetMapping("index")
    public String index(){
        return "Welcome to management page.";
    }
}
