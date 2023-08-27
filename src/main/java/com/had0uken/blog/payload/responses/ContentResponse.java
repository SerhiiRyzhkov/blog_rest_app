package com.had0uken.blog.payload.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentResponse<T> {
    private List<T> content;

    public int getSize(){
        return content.size();
    }

}
