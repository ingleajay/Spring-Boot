package com.sboot.blog.payload;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {

    private long id;

    @NotEmpty(message = "Name should not empty or null")
    @Size(min = 4, message = "Name should have at least 4 characters")
    private String name;

    @NotEmpty(message = "Email should not empty or null")
    @Email
    private String email;

    @NotEmpty(message = "Body is not empty or null")
    @Size(min = 10, message = "Body should have at least 10 characters")
    private String body;
}
