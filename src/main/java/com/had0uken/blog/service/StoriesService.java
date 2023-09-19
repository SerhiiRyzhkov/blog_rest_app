package com.had0uken.blog.service;

import com.had0uken.blog.model.post.Stories;
import com.had0uken.blog.payload.responses.Response;
import org.springframework.security.core.Authentication;

public interface StoriesService {
    Response getAllStories();
    Response getStories(Long id);
    Response getStoriesByTag(String tag);
    Response addNewStories(Stories stories, Authentication authentication);
    Response deleteStories(Long id, Authentication authentication);
    Response likeStories(Long id, Authentication authentication);
    Response repostedStories(Long id, Authentication authentication);
}