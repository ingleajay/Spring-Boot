package com.sboot.blog.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

// to mention fields which is required to send to client only

@Data
public class PostDtoV2 {

    private long id;

    // title should not be empty or null and it should contain at least length 2 characters.
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    // post decription not empty and null and size at least 10 char
    @NotEmpty
    @Size(min = 10,message = "Post description should have at least 10  characters")
    private String description;

    @NotEmpty
    private String content;
    private Set<CommentDto> comments;

    private List<String> tags;
}
