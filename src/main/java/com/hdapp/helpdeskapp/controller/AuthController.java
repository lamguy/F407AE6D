package com.hdapp.helpdeskapp.controller;

import com.hdapp.helpdeskapp.model.User;
import com.hdapp.helpdeskapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public Mono<User> createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }
}
