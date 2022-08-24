package com.laurentiuspilca.ssia.controllers;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Mono<String> hello() {
        Mono<String> message =
                ReactiveSecurityContextHolder.getContext()
                        .map(context -> context.getAuthentication())
                        .map(auth -> "Hello " + auth.getName());
        return message;
    }
}
