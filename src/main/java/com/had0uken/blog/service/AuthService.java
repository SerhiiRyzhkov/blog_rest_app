package com.had0uken.blog.service;


import com.had0uken.blog.security.AuthRequest;
import com.had0uken.blog.security.AuthResponse;
import com.had0uken.blog.security.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse authenticate(AuthRequest request);
}