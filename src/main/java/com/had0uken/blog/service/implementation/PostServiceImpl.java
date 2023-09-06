package com.had0uken.blog.service.implementation;

import com.had0uken.blog.model.user.Post;
import com.had0uken.blog.model.user.Role;
import com.had0uken.blog.model.user.User;
import com.had0uken.blog.payload.responses.ApiResponse;
import com.had0uken.blog.payload.responses.ContentResponse;
import com.had0uken.blog.payload.responses.Response;
import com.had0uken.blog.repository.PostRepository;
import com.had0uken.blog.repository.UserRepository;
import com.had0uken.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public Response getAllPosts() {
        return new ContentResponse<>(postRepository.findAll(),HttpStatus.OK);
    }

    @Override
    public Response getPost(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if(postOptional.isPresent())
        return new ContentResponse<>(List.of(postOptional.get()),HttpStatus.OK);
        else return new ApiResponse("Post not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public Response addNewPost(Post post) {
        postRepository.save(post);
        return new ApiResponse("Post created successfully",HttpStatus.CREATED);
    }

    @Override
    public Response updatePost(Post post, Long id, Authentication authentication) {
        Optional<Post> optional = postRepository.findById(id);
        if(optional.isPresent()){
            Post existingPost = optional.get();
            if(!checkAccess(existingPost,authentication))
                return new ApiResponse("You do not have permission to delete this post",HttpStatus.FORBIDDEN);
            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());
            postRepository.save(existingPost);
            return new ApiResponse("Post updated successfully",HttpStatus.OK);
        }
        else
            return new ApiResponse("Post was not found",HttpStatus.NOT_FOUND);
    }

    @Override
    public Response deletePost(Long id, Authentication authentication) {
        Optional<Post>optional = postRepository.findById(id);
        if(optional.isPresent()){
            Post existingPost = optional.get();
            if(!checkAccess(existingPost,authentication))
                return new ApiResponse("You do not have permission to delete this post",HttpStatus.FORBIDDEN);
            postRepository.delete(existingPost);
            return new ApiResponse("Post deleted successfully",HttpStatus.NO_CONTENT);
        }
        else
            return new ApiResponse("Post was not found",HttpStatus.NOT_FOUND);
    }


    private boolean checkAccess(Post post, Authentication authentication)
    {
        User user = userRepository.findByEmail(authentication.getName()).get();
        return ((post.getUser().equals(user))||(user.getAuthorities().contains(Role.ADMIN))
                ||(user.getAuthorities().contains(Role.MODERATOR)));
    }

}