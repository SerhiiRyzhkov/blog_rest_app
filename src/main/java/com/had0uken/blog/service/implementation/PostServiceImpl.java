package com.had0uken.blog.service.implementation;

import com.had0uken.blog.model.user.Post;
import com.had0uken.blog.model.user.Role;
import com.had0uken.blog.model.user.User;
import com.had0uken.blog.repository.PostRepository;
import com.had0uken.blog.repository.UserRepository;
import com.had0uken.blog.security.UserPrincipal;
import com.had0uken.blog.service.PostService;
import jakarta.persistence.EntityNotFoundException;
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
    public Post findPostById(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public List<Post> findPostsByAuthorId(Long id) {
        return postRepository.findByUser_Id(id);
    }

    @Override
    public void addNewPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void updatePost(Long id, Post post) {
        Optional<Post> existingPostOptional = postRepository.findById(id);
        if (existingPostOptional.isPresent()) {
            Post existingPost = existingPostOptional.get();
            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());
            postRepository.save(existingPost);
        } else {
            throw new EntityNotFoundException("Post with ID " + id + " not found");
        }
    }

    @Override
    public boolean isAllowed(Long id, User user) {
        if((user.getAuthorities().contains(Role.MODERATOR))||user.getAuthorities().contains(Role.ADMIN))return true;
        Optional<Post> post = postRepository.findById(id);
        return post.map(value -> value.getUser().equals(user)).orElse(false);
    }
}