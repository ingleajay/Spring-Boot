package com.sboot.blog.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sboot.blog.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

// to mention fields which is required to send to client only

@ApiModel(value = "Post Model DTO")
@Data
public class PostDto {

    private long id;

    // title should not be empty or null and it should contain at least length 2 characters.
    @ApiModelProperty(value = "Title of post")
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    // post decription not empty and null and size at least 10 char
    @ApiModelProperty(value = "Description of post")
    @NotEmpty
    @Size(min = 10,message = "Post description should have at least 10  characters")
    private String description;

    @NotEmpty
    @ApiModelProperty(value = "Content of post")
    private String content;

    @ApiModelProperty(value = "All commnets on post")
    private Set<CommentDto> comments;

    @NotEmpty
    private String imageUrl;

    private User users;

    private Date dateCreated;
    private Date dateUpdated;
}
