package com.had0uken.blog.service;

import com.had0uken.blog.model.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CustomUserDetailsService extends UserDetailsService {

    List<User> findAllUsers();



}