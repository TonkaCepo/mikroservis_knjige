package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/com/example/demo/user")
    public String userEndpoint() {
        return "Hello USER";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Hello ADMIN";
    }
}
