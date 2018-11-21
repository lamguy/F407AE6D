package com.hdapp.helpdeskapp.controller;

import com.hdapp.helpdeskapp.model.User;
import com.hdapp.helpdeskapp.service.UserService;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    private final MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;
    private final UserService userRepository;

    public UserRestController(UserService userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    Publisher<User> getAll() {
        return this.userRepository.all();
    }

    @GetMapping("/{id}")
    Publisher<User> getById(@PathVariable("id") String id) {
        return this.userRepository.get(id);
    }
}
