package com.had0uken.blog.service;

import com.had0uken.blog.model.user.User;
import com.had0uken.blog.payload.responses.Response;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface CustomUserDetailsService extends UserDetailsService {
    Response getPostsByUser(String username);

    Response getStoriesByUser(String username);

    Response addNewUser(User newUser, Authentication authentication, PasswordEncoder passwordEncoder);

    Response updateUser(String username, User updUser, Authentication authentication, PasswordEncoder passwordEncoder);

    Response deleteUser(String username, Authentication authentication);
}