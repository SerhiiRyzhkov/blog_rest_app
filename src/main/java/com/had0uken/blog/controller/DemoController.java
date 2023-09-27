package com.had0uken.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {
    @GetMapping("/demo-controller")
    public ResponseEntity<String> hello() {
        System.out.println("enter to demo method");
        return ResponseEntity.ok("Hello from demo controller");
    }
}
