package com.example.demo.controller;


import com.example.demo.model.Image;
import com.example.demo.model.Session;
import com.example.demo.model.UserAuthPojo;
import com.example.demo.repo.SessionRepository;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public Session getSession(@RequestBody UserAuthPojo userAuthPojo){
        return authenticationService.authenticate(userAuthPojo);
    }
}
