package com.vigfoot.ipmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Forwarder {

    @GetMapping("/")
    public String yourIpAddress(Model model){

        return "index";
    }
}
