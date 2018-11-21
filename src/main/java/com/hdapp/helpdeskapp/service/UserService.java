package com.hdapp.helpdeskapp.service;

import com.hdapp.helpdeskapp.event.UserCreatedEvent;
import com.hdapp.helpdeskapp.model.User;
import com.hdapp.helpdeskapp.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class UserService {
    private final ApplicationEventPublisher publisher;
    private final UserRepository userRepository;


    public UserService(ApplicationEventPublisher publisher, UserRepository userRepository) {
        this.publisher = publisher;
        this.userRepository = userRepository;
    }

    public Flux<User> all() {
        return this.userRepository.findAll();
    }

    public Mono<User> get(String id) {
        return this.userRepository.findById(id);
    }

    public Mono<User> update(String id, String email, String password) {
        return this.userRepository
                .findById(id)
                .map(user -> new User(id, email, password))
                .flatMap(this.userRepository::save);
    }

    public Mono<User> delete(String id) {
        return this.userRepository
                .findById(id)
                .flatMap(user -> this.userRepository.deleteById(user.getId()).thenReturn(user));
    }

    public Mono<User> create(String email, String password) {
        return this.userRepository
                .save(new User(email, password))
                .doOnSuccess(user -> this.publisher.publishEvent(new UserCreatedEvent(user)));
    }
}
