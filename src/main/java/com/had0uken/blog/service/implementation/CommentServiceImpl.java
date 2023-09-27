package com.had0uken.blog.service.implementation;

import com.had0uken.blog.model.post.Comment;
import com.had0uken.blog.model.post.Post;
import com.had0uken.blog.model.user.User;
import com.had0uken.blog.payload.responses.ApiResponse;
import com.had0uken.blog.payload.responses.ContentResponse;
import com.had0uken.blog.payload.responses.Response;
import com.had0uken.blog.repository.CommentRepository;
import com.had0uken.blog.repository.PostRepository;
import com.had0uken.blog.repository.UserRepository;
import com.had0uken.blog.access.Access;
import com.had0uken.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final Access access;

    @Override
    public Response getCommentsByPost(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent())
            return new ContentResponse<>(List.of(postOptional.get().getComments()), HttpStatus.OK);
        else return new ApiResponse("Post not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public Response getCommentById(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent())
            return new ContentResponse<>(List.of(commentOptional.get()), HttpStatus.OK);
        else return new ApiResponse("Comment not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public Response addNewComment(Comment comment, Long id, Authentication authentication) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            User user = userRepository.findByEmail(authentication.getName()).get();
            comment.setUser(user);
            comment.setCreated(LocalDate.now());
            comment.setPost(postOptional.get());
            commentRepository.save(comment);
            return new ApiResponse("Comment was added", HttpStatus.OK);
        } else return new ApiResponse("Post was not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public Response updateComment(Comment comment, Long id, Authentication authentication) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            Comment existingComment = commentOptional.get();
            if (!access.editCheckAccess(existingComment, authentication))
                return new ApiResponse("You do not have permission to update this comment", HttpStatus.FORBIDDEN);
            existingComment.setText(comment.getText());
            commentRepository.save(existingComment);
            return new ApiResponse("Comment was updated", HttpStatus.OK);
        } else return new ApiResponse("Comment was not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public Response deleteComment(Long id, Authentication authentication) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            Comment existingComment = commentOptional.get();
            if (!access.deleteCheckAccess(existingComment, authentication))
                return new ApiResponse("You do not have permission to delete this comment", HttpStatus.FORBIDDEN);
            commentRepository.delete(commentOptional.get());
            return new ApiResponse("Comment was deleted", HttpStatus.OK);
        } else return new ApiResponse("Comment was not found", HttpStatus.NOT_FOUND);
    }


}
