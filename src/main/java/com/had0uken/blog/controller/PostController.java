package com.had0uken.blog.controller;

import com.had0uken.blog.model.user.Post;
import com.had0uken.blog.payload.responses.PageResponse;
import com.had0uken.blog.service.CustomUserDetailsService;
import com.had0uken.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


    @GetMapping("/getAllPosts")
    public ResponseEntity<PageResponse<Post>> getAllPosts(){
        System.out.println("here8");
        System.out.println(postService.findAllPosts());
        PageResponse<Post> response = new PageResponse<>(postService.findAllPosts());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getPostById")
    public ResponseEntity<PageResponse<Post>> getPostById(@PathVariable(name = "id") Long id){
        Post post = postService.findPostById(id).get();
        PageResponse<Post> response = new PageResponse<>(new ArrayList<>(List.of(post)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
