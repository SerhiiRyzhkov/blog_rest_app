package com.had0uken.blog.service;

import com.had0uken.blog.model.user.Post;
import com.had0uken.blog.model.user.User;
import com.had0uken.blog.security.UserPrincipal;

import java.util.List;

public interface PostService {
    List<Post> findAllPosts();
    Post findPostById(Long id);

    List<Post> findPostsByAuthorId(Long id);
    void addNewPost(Post post);
    void updatePost(Long id, Post post);
    boolean isAllowed(Long id, User user);
}
