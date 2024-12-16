package com.oyameen.SpringBootSecurity2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("")
    public RedirectView redirectView() {
        return new RedirectView("/index");
    }

    @GetMapping("index")
    public String index() {
        return "Welcome to home page.";
    }

}
