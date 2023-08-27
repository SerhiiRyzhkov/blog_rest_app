package com.had0uken.blog.controller;

import com.had0uken.blog.model.user.Post;
import com.had0uken.blog.model.user.User;
import com.had0uken.blog.payload.responses.ContentResponse;
import com.had0uken.blog.security.UserPrincipal;
import com.had0uken.blog.service.CustomUserDetailsService;
import com.had0uken.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CustomUserDetailsService userService;


    @GetMapping("/")
    public ResponseEntity<ContentResponse<Post>> getAllPosts(){
        ContentResponse<Post> response = new ContentResponse<>(postService.findAllPosts());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContentResponse<Post>> getPostsById(@PathVariable Long id){
        ContentResponse<Post> response = new ContentResponse<>(List.of(postService.findPostById(id)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ContentResponse<Post>> addNewPost(@RequestBody Post post, Authentication authentication){
        post.setUser((User) userService.loadUserByUsername(authentication.getName()));
        postService.addNewPost(post);
        ContentResponse<Post> response = new ContentResponse<>(List.of(postService.findPostById(post.getId())));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

 /*   @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<ContentResponse<Post>> updatePost(@PathVariable Long id, @RequestBody Post post, Authentication authentication){
        if(postService.isAllowed(id, (User) userService.loadUserByUsername(authentication.getName())))
            postService.updatePost(id,post);
        ContentResponse<Post> response = new ContentResponse<>(List.of(postService.findPostById(post.getId())));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/






}

