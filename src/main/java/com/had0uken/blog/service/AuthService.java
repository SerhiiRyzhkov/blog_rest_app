package com.had0uken.blog.service;


import com.had0uken.blog.payload.requests.AuthRequest;
import com.had0uken.blog.payload.requests.RegisterRequest;
import com.had0uken.blog.payload.responses.Response;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface AuthService {
    Response register(RegisterRequest request);
    Response authenticate(AuthRequest request);
    PasswordEncoder getEncoder();


}