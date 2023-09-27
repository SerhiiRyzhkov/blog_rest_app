package com.had0uken.blog.controller;

import com.had0uken.blog.model.post.Stories;
import com.had0uken.blog.payload.responses.Response;
import com.had0uken.blog.service.StoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stories")
@RequiredArgsConstructor
public class StoriesController {

    private final StoriesService storiesService;


    @GetMapping("/")
    public ResponseEntity<Response> getAllStories() {
        Response response = storiesService.getAllStories();
        return new ResponseEntity<>(response, response.getStatus());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response> getStoriesById(@PathVariable Long id) {
        Response response = storiesService.getStories(id);
        return new ResponseEntity<>(response, response.getStatus());
    }


    @PostMapping("/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Response> addNewStories(@RequestBody Stories stories, Authentication authentication) {
        Response response = storiesService.addNewStories(stories, authentication);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<Response> deleteStories(@PathVariable Long id, Authentication authentication) {
        Response response = storiesService.deleteStories(id, authentication);
        return new ResponseEntity<>(response, response.getStatus());
    }


    @PostMapping("/{id}/like")
    public ResponseEntity<Response> likeStories(@PathVariable Long id, Authentication authentication) {
        Response response = storiesService.likeStories(id, authentication);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/{id}/repost")
    public ResponseEntity<Response> repostStories(@PathVariable Long id, Authentication authentication) {
        Response response = storiesService.repostedStories(id, authentication);
        return new ResponseEntity<>(response, response.getStatus());
    }

}