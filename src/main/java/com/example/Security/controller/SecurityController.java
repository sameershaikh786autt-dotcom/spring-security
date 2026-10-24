package com.example.Security.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecurityController {

    @GetMapping("/access")
    public String access() {
        return "Hello World I'm Security Controller";
    }


    // METHOD SECURITY AUTHENTICATION
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/login")
    public String login() {
        return "login"; // Thymeleaf template name
    }


    // METHOD SECURITY AUTHENTICATION
    @PreAuthorize(("hasRole('ADMIN')"))
    @GetMapping("/admin")
    public String admin() {
        return "Hello admin";
    }

}
