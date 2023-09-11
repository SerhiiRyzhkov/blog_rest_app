package com.had0uken.blog.service.implementation;

import com.had0uken.blog.model.post.MediaFile;
import com.had0uken.blog.model.post.Post;
import com.had0uken.blog.model.user.Role;
import com.had0uken.blog.model.user.User;
import com.had0uken.blog.payload.responses.ApiResponse;
import com.had0uken.blog.payload.responses.ContentResponse;
import com.had0uken.blog.payload.responses.Response;
import com.had0uken.blog.repository.MediaRepository;
import com.had0uken.blog.repository.PostRepository;
import com.had0uken.blog.repository.UserRepository;
import com.had0uken.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final MediaRepository mediaRepository;

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
    public Response addNewPost(Post post,Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).get();
        post.setUser(user);
        post.setCreated(LocalDate.now());
        postRepository.save(post);
        return new ApiResponse("Post created successfully",HttpStatus.CREATED);
    }

    @Override
    public Response updatePost(Post post, Long id, Authentication authentication) {
        Optional<Post> optional = postRepository.findById(id);
        if(optional.isPresent()){
            Post existingPost = optional.get();
            if(!checkAccess(existingPost,authentication))
                return new ApiResponse("You do not have permission to update this post",HttpStatus.FORBIDDEN);
            if(post.getTitle()!=null)
            existingPost.setTitle(post.getTitle());
            if(post.getBody()!=null)
            existingPost.setBody(post.getBody());
            if(post.getMediaFiles()!=null) {
              List<MediaFile> deleteList = existingPost.getMediaFiles();
              existingPost.setMediaFiles(post.getMediaFiles());
              mediaRepository.deleteAll(deleteList);
            }
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

    @Override
    public Response likePost(Long id, Authentication authentication) {
        Optional<Post> optional=postRepository.findById(id);
        if(optional.isPresent()) {
            Post existingPost = optional.get();
            User user = userRepository.findByEmail(authentication.getName()).get();
            Response response;
            if (!existingPost.getLikedByUsers().contains(user)) {
                existingPost.getLikedByUsers().add(user);
                user.getLikedPosts().add(existingPost);
                response = new ApiResponse("Post liked successfully", HttpStatus.OK);
            } else {
                existingPost.getLikedByUsers().remove(user);
                user.getLikedPosts().remove(existingPost);
                response = new ApiResponse("Post unliked successfully", HttpStatus.OK);
            }
            userRepository.save(user);
            postRepository.save(existingPost);
            return response;
        }
        else
            return new ApiResponse("Post was not found",HttpStatus.NOT_FOUND);
    }

    @Override
    public Response repostedPost(Long id, Authentication authentication) {
        Optional<Post> optional=postRepository.findById(id);
        if(optional.isPresent()) {
            Post existingPost = optional.get();
            User user = userRepository.findByEmail(authentication.getName()).get();
            if(existingPost.getUser().equals(user)) return new ApiResponse("You are not allowed to repost your own post", HttpStatus.FORBIDDEN);

            Response response;

            if (!existingPost.getRepostedByUsers().contains(user)) {
                existingPost.getRepostedByUsers().add(user);
                user.getRepostedPosts().add(existingPost);
                response = new ApiResponse("Post reposted successfully", HttpStatus.OK);
            } else {
                response = new ApiResponse("Post already reposted", HttpStatus.OK);
            }
            userRepository.save(user);
            postRepository.save(existingPost);
            return response;
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