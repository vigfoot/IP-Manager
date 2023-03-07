package com.vigfoot.ipmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@RestController
public class Controller {

    @GetMapping("/ip/verify")
    public Mono<Boolean> verifyConnection() {
        try(Socket socket = new Socket()){
            socket.connect(new InetSocketAddress("google.com", 80));
            System.out.println(socket.getLocalAddress().getHostAddress());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Mono.empty();
    }
}