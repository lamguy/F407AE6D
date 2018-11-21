package com.hdapp.helpdeskapp.handler;

import com.hdapp.helpdeskapp.model.User;
import com.hdapp.helpdeskapp.service.UserService;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class UserHandler {
    private final UserService userService;

    UserHandler(UserService userService) {
        this.userService = userService;
    }

    private static Mono<ServerResponse> defaultReadResponse(Publisher<User> users) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(users, User.class);
    }

    private static Mono<ServerResponse> defaultWriteResponse(Publisher<User> users) {
        return Mono
                .from(users)
                .flatMap(user -> ServerResponse
                    .created(URI.create("/users/" + user.getId()))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .build()
                );
    }

    private static String id(ServerRequest request) {
        return request.pathVariable("id");
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        return defaultReadResponse(this.userService.get(id(request)));
    }

    public Mono<ServerResponse> all(ServerRequest r) {
        return defaultReadResponse(this.userService.all());
    }
}
