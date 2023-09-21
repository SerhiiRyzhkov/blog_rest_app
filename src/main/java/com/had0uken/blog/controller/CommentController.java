package com.had0uken.blog.controller;

import com.had0uken.blog.model.post.Comment;
import com.had0uken.blog.model.post.Post;
import com.had0uken.blog.payload.responses.Response;
import com.had0uken.blog.service.CommentService;
import com.had0uken.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}/post")
    public ResponseEntity<Response> getCommentsByPost(@PathVariable Long id){
        Response response = commentService.getCommentsByPost(id);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCommentsById(@PathVariable Long id) {
        Response response = commentService.getCommentById(id);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @PostMapping("/{id}/post")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Response> addNewComment(@RequestBody Comment comment, @PathVariable Long id, Authentication authentication){
        Response response = commentService.addNewComment(comment, id, authentication);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Response> updateComment(@RequestBody Comment comment, @PathVariable Long id, Authentication authentication){
        Response response = commentService.updateComment(comment, id, authentication);
        return new ResponseEntity<>(response,response.getStatus());
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Response> deleteComment(@PathVariable Long id, Authentication authentication) {
        Response response = commentService.deleteComment(id, authentication);
        return new ResponseEntity<>(response,response.getStatus());
    }

}
