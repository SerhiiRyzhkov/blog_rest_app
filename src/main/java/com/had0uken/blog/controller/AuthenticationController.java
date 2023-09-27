package com.had0uken.blog.controller;

import com.had0uken.blog.payload.requests.AuthRequest;
import com.had0uken.blog.payload.responses.ApiResponse;
import com.had0uken.blog.payload.responses.AuthResponse;
import com.had0uken.blog.payload.requests.RegisterRequest;
import com.had0uken.blog.payload.responses.Response;
import com.had0uken.blog.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody RegisterRequest request){
        Response response = authService.register(request);
        return new ResponseEntity<>(response,response.getStatus());
    }


    @PostMapping("/authenticate")
    public ResponseEntity<Response> authenticate(@RequestBody AuthRequest request){
        Response response = authService.authenticate(request);
        return new ResponseEntity<>(response,response.getStatus());
    }
}