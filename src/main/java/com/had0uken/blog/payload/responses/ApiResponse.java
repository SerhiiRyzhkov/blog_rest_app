package com.had0uken.blog.payload.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse extends Response implements Serializable {
    @Serial
    @JsonIgnore
    private static final long serialVersionUID = -317791867932483677L;

    @JsonProperty("message")
    private String message;

    @JsonIgnore
    HttpStatus httpStatus;

    @JsonIgnore
    @Override
    public HttpStatus getStatus() {
        return httpStatus;
    }
}
