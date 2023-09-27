package com.had0uken.blog.controller;

import com.had0uken.blog.payload.responses.Response;
import com.had0uken.blog.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping("/{tagName}/posts")
    public ResponseEntity<Response> getPostsByTag(@PathVariable String tagName) {
        Response response = tagService.getPostsByTag(tagName);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/{tagName}/stories")
    public ResponseEntity<Response> getStoriesByTag(@PathVariable String tagName) {
        Response response = tagService.getStoriesByTag(tagName);
        return new ResponseEntity<>(response, response.getStatus());
    }
}

