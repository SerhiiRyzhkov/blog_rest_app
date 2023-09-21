package com.had0uken.blog.service;

import com.had0uken.blog.model.post.Comment;
import com.had0uken.blog.model.post.Post;
import com.had0uken.blog.payload.responses.Response;
import org.springframework.security.core.Authentication;

public interface CommentService {
    Response getCommentsByPost(Long id);

    Response getCommentById(Long id);

    Response addNewComment(Comment comment, Long id, Authentication authentication);

    Response updateComment(Comment comment, Long id, Authentication authentication);

    Response deleteComment(Long id, Authentication authentication);
}
