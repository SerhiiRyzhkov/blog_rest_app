package com.had0uken.blog.service;

import com.had0uken.blog.model.user.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    public List<Post> findAllPosts();
    public Optional<Post> findPostById(Long id);
}
