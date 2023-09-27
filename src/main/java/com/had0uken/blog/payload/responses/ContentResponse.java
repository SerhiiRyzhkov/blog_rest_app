package com.had0uken.blog.payload.responses;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;



@AllArgsConstructor
@NoArgsConstructor
public class ContentResponse<T>  extends Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 6355399315461466325L;
    @JsonProperty
    private List<T> content;

    @JsonIgnore
    HttpStatus httpStatus;

    @JsonIgnore
    @Override
    public HttpStatus getStatus() {
        return httpStatus;
    }
}
