package com.had0uken.blog.service;

import org.springframework.security.core.userdetails.UserDetails;
import java.util.Map;

public interface JwtService {
    String extractUsername(String jwt);
    String generateToken(UserDetails userDetails);
    public String generateToken(Map<String,Object> claims, UserDetails userDetails);
    public boolean isJwtValid(String jwt, UserDetails userDetails);
}
