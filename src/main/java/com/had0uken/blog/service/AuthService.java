package com.had0uken.blog.service;


import com.had0uken.blog.payload.requests.AuthRequest;
import com.had0uken.blog.payload.responses.AuthResponse;
import com.had0uken.blog.payload.requests.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthRequest request);
    PasswordEncoder getEncoder();


}