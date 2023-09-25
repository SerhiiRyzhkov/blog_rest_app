package com.had0uken.blog.controller;

import com.had0uken.blog.model.user.User;
import com.had0uken.blog.payload.responses.Response;
import com.had0uken.blog.service.AuthService;
import com.had0uken.blog.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final CustomUserDetailsService userService;
    private final AuthService authService;

    @GetMapping("/{username}/posts")
    public ResponseEntity<Response> getPostsByUser(@PathVariable String username) {
        Response response = userService.getPostsByUser(username);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @GetMapping("/{username}/stories")
    public ResponseEntity<Response> getStoriesByUser(@PathVariable String username) {
        Response response = userService.getStoriesByUser(username);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @PostMapping("/")
    public ResponseEntity<Response> addNewUser(@RequestBody User newUser, Authentication authentication){
        Response response = userService.addNewUser(newUser,authentication, authService.getEncoder());
        return new ResponseEntity<>(response,response.getStatus());
    }

    @PutMapping("/{username}")
    public ResponseEntity<Response> updateUser(@PathVariable String username, @RequestBody User updUser, Authentication authentication){
        Response response = userService.updateUser(username, updUser, authentication, authService.getEncoder());
        return new ResponseEntity<>(response,response.getStatus());
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Response> deleteUser(@PathVariable String username, Authentication authentication){
        Response response = userService.deleteUser(username,authentication);
        return new ResponseEntity<>(response,response.getStatus());
    }

}
