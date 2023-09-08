package com.had0uken.blog.controller;

import com.had0uken.blog.model.post.Post;
import com.had0uken.blog.model.user.User;
import com.had0uken.blog.payload.responses.Response;
import com.had0uken.blog.service.CustomUserDetailsService;
import com.had0uken.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CustomUserDetailsService userService;


    @GetMapping("/")
    public ResponseEntity<Response> getAllPosts(){
        Response response = postService.getAllPosts();
        return new ResponseEntity<>(response,response.getStatus());
    }



    @GetMapping("/{id}")
    public ResponseEntity<Response> getPostById(@PathVariable Long id)
    {
        Response response = postService.getPost(id);
        return new ResponseEntity<>(response,response.getStatus());
    }


    @PostMapping("/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Response> addNewPost(@RequestBody Post post, Authentication authentication){
      /*Post post = Post.builder()
                .title(postRequest.getTitle())
                .body(postRequest.getBody())
                .photos(postRequest.getPhotos())
                .build();*/
        Response response = postService.addNewPost(post,authentication);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Response> updatePost(@PathVariable Long id, @RequestBody Post post, Authentication authentication){
        Response response = postService.updatePost(post,id,authentication);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<Response> deletePost(@PathVariable Long id, Authentication authentication){
        Response response = postService.deletePost(id,authentication);
        return new ResponseEntity<>(response,response.getStatus());
    }


    @PostMapping("/{id}/like")
    public ResponseEntity<Response> likePost (@PathVariable Long id, Authentication authentication){
        Response response = postService.likePost(id,authentication);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @PostMapping("/{id}/repost")
    public ResponseEntity<Response> repostPost (@PathVariable Long id, Authentication authentication){
        Response response = postService.repostedPost(id,authentication);
        return new ResponseEntity<>(response,response.getStatus());
    }

}

