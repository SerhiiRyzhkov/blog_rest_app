package com.had0uken.blog.service;

import com.had0uken.blog.model.Post;
import com.had0uken.blog.model.user.User;
import com.had0uken.blog.payload.responses.ContentResponse;
import com.had0uken.blog.payload.responses.Response;
import com.had0uken.blog.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface PostService {
    Response getAllPosts();
    Response getPost(Long id);
    Response addNewPost(Post post);
    Response updatePost(Post post, Long id, Authentication authentication);
    Response deletePost(Long id, Authentication authentication);
    Response likePost(Long id, Authentication authentication);
    Response repostedPost(Long id, Authentication authentication);

    Post findPost(Long id);
}
