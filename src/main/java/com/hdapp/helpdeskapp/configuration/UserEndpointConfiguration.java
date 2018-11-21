package com.hdapp.helpdeskapp.configuration;

import com.hdapp.helpdeskapp.handler.UserHandler;
import com.hdapp.helpdeskapp.predicate.CaseInsensitiveRequestPredicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
class UserEndpointConfiguration {

    @Bean
    RouterFunction<ServerResponse> routes(UserHandler userHandler) {
        return route(i(GET("/users")), userHandler::all)
                .andRoute(i(GET("/users/{id}")), userHandler::getById);
    }

    private static RequestPredicate i(RequestPredicate target) {
        return new CaseInsensitiveRequestPredicate(target);
    }
}
