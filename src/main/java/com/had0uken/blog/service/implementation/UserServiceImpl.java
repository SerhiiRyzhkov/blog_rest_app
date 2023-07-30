package com.had0uken.blog.service.implementation;

import com.had0uken.blog.model.user.User;
import com.had0uken.blog.repository.UserRepository;
import com.had0uken.blog.service.CustomUserDetailsService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements CustomUserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }


    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}