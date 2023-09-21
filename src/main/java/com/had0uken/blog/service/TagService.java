package com.had0uken.blog.service;

import com.had0uken.blog.model.Tag;
import com.had0uken.blog.payload.responses.Response;

public interface TagService {

    Response getStoriesByTag(String tag);
    Response getPostsByTag(String tag);
}
