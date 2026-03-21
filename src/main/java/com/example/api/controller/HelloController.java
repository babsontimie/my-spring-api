package com.example.api.controller;

import com.example.api.model.GreetRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HelloController {

    @Value("${app.env:dev}")
    private String appEnv;

    @Value("${spring.application.name:my-spring-api}")
    private String appName;

    @GetMapping
    public ResponseEntity<Map<String, String>> root() {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("application", appName);
        response.put("message", "API is running");
        response.put("environment", appEnv);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("status", "ok");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hello/{name}")
    public ResponseEntity<Map<String, String>> hello(@PathVariable String name) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("message", "Hello, " + name + "!");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/greet")
    public ResponseEntity<Map<String, String>> greet(@Valid @RequestBody GreetRequest request) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("message", "Welcome, " + request.getName() + "!");
        return ResponseEntity.ok(response);
    }
}
