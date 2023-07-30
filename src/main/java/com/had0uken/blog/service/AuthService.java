package com.had0uken.blog.service;


import com.had0uken.blog.payload.requests.AuthRequest;
import com.had0uken.blog.payload.responses.AuthResponse;
import com.had0uken.blog.payload.requests.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse authenticate(AuthRequest request);
}