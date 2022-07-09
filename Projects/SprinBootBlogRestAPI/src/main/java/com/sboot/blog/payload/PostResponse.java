package com.sboot.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// This class is for pagination response to client
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private  boolean last;

}
