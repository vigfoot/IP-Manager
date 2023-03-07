package com.vigfoot.ipmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class Controller {

    @GetMapping("/ip/verify")
    public Mono<Boolean> verifyConnection() {
        return Mono.empty();
    }
}