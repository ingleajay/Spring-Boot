package com.sboot.blog.controller;

import com.sboot.blog.entity.Post;
import com.sboot.blog.payload.PostDto;
import com.sboot.blog.payload.PostDtoV2;
import com.sboot.blog.payload.PostResponse;
import com.sboot.blog.repository.UserRepository;
import com.sboot.blog.service.PostService;
import com.sboot.blog.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

// it uses @controller and @RequestBody internally it uses when work with rest api
@Api(value = "CRUD REST API for Post Resources")
@RestController
@RequestMapping()
public class PostController {

    // try to inject interface not class here so your class will become loose couple
    //no need of @Autowired as we have only one constructor
    private PostService postService;



    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create blog post
    // we have to take DTO as input to send to client
    @ApiOperation(value = "Create Post")
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/user/{userId}/posts/")
    public ResponseEntity<PostDto> createPosts(@Valid @RequestBody PostDto postDto, @PathVariable(name = "userId") Long userId){
        return new ResponseEntity<>(postService.createPost(postDto,userId), HttpStatus.CREATED);
    }

    // Get All posts
    @ApiOperation(value = "Get All Post")
    @GetMapping("/api/v1/posts")
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir
            ){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    // get post by id
    @ApiOperation(value = "Get Post By id")
    @GetMapping("/api/v1/posts/{id}")
    //@GetMapping(value = "/api/posts/{id}", params = "version=1")
   // @GetMapping(value = "/api/posts/{id}", headers = "X-API-VERSION=1")
   // @GetMapping(value = "/api/posts/{id}",produces = "application/vnd.codeouter.v1+json")
    public ResponseEntity<PostDto> getPostByID(@PathVariable(name="id") long id){
        return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }

    // just for undedstanding versioning in spring boot
    // for version 2 api = versioning by URI Path => http://localhost:8080/api/v2/posts/1
    @GetMapping("/api/v2/posts/{id}")
    @ApiOperation(value = "Get by id in v2 Post")
    // for version 2 api = versioning by query parameter => http://localhost:8080/api/posts/1?version=2
    // @GetMapping(value = "/api/posts/{id}", params = "version=2")
    // for version 2 api = versioning by custom header => http://localhost:8080/api/posts/1 => set headers in postman
    // @GetMapping(value = "/api/posts/{id}",headers = "X-API-VERSION=2")
    // for version 2 api = versioning by content negotiation => http://localhost:8080/api/posts/1 => set headers in postman
    // @GetMapping(value = "/api/posts/{id}",produces = "application/vnd.codeouter.v2+json")
    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable(name="id") long id){
        PostDto postDto = postService.getPostById(id);
        PostDtoV2 postDtoV2 = new PostDtoV2();
        postDtoV2.setId(postDto.getId());
        postDtoV2.setTitle(postDto.getTitle());
        postDtoV2.setDescription(postDto.getDescription());
        postDtoV2.setContent(postDto.getContent());
        postDtoV2.setComments(postDto.getComments());
        List<String> tags = new ArrayList<>();
        tags.add("Java");
        tags.add("Spring Boot");
        tags.add("AWS");
        postDtoV2.setTags(tags);
        return  ResponseEntity.ok(postDtoV2);
    }


    // update post by id
    @ApiOperation(value = "Update Post")
    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/v1/user/{userId}/posts/{id}")
    public  ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name ="id") long id ,@PathVariable(name = "userId") Long userId ){
        return new ResponseEntity<>(postService.updatePost(postDto,id,userId),HttpStatus.OK);
    }

    // delete post by id
    @ApiOperation(value = "Delete Post")
   // @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/v1/user/{userId}/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id, @PathVariable(name = "userId") Long userId){
        postService.deletePostById(id,userId);
        return new ResponseEntity<>("Post deleted successfully !!", HttpStatus.OK);
    }

    // search post with title and description

    @GetMapping("/api/v1/posts/search")
    @ApiOperation(value = "Search Post By Title and description")
    public  ResponseEntity<List<PostDto>> searchPost(@RequestParam("query") String query){
        return  ResponseEntity.ok(postService.searchPost(query));
    }

}

//{
//
//        "content": "This is first post that you can read",
//        "dateCreated": "2022-07-09T06:25:53.475Z",
//        "dateUpdated": "2022-07-09T06:25:53.475Z",
//        "description": "This is something new you can learn !! ",
//        "id": 0,
//        "imageUrl": "default.png",
//        "title": "Learn springboot with docker "
//}