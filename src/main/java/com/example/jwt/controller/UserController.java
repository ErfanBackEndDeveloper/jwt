package com.example.jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
//    @PreAuthorize("hasAnyRole('STUDENT','ADMIN')")
    @PreAuthorize("hasAnyRole('Admin')")
    public void save(){
        System.out.println("saving user");
    }

    @GetMapping
//    @PreAuthorize("hasAnyRole('STUDENT','ADMIN')")
    @PreAuthorize("hasAnyRole('subAdmin')")
    public void find(){
        System.out.println("saving user");
    }
}
