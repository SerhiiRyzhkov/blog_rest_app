package com.had0uken.blog.payload.responses;

import org.springframework.http.HttpStatus;

public abstract class Response {
    public abstract HttpStatus getStatus();
}
