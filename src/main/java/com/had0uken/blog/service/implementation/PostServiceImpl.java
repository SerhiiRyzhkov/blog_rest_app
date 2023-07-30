package com.had0uken.blog.service.implementation;

import com.had0uken.blog.model.user.Post;
import com.had0uken.blog.repository.PostRepository;
import com.had0uken.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    public List<Post> findAllPosts(){
        return postRepository.findAll();
    }
    public Optional<Post> findPostById(Long id) {return postRepository.findById(id);}
}
