package com.sboot.blog.controller;

import com.sboot.blog.payload.CommentDto;
import com.sboot.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD Comment REST API")
@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // post your comments
    @ApiOperation(value = "Create comments")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId, @Valid @RequestBody CommentDto commentDto){
            return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    // get comments by id
    @ApiOperation(value = "Get Comments by Id")
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentByPostId(@PathVariable(name = "postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    @ApiOperation(value = "Get post Id by comments id")
    @GetMapping("/posts/{postId}/comments/{id}")
    public  ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId, @PathVariable(value = "id") Long commentId){
            CommentDto commentDto = commentService.getCommentById(postId,commentId);
            return  new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/posts/{postId}/comments/{id}")
    @ApiOperation(value = "update comment by id")
    public  ResponseEntity<CommentDto> updateCommentById(@PathVariable(value = "postId") Long postId, @PathVariable(value = "id") Long commentId ,@Valid @RequestBody CommentDto commentDto){
        return  new ResponseEntity<>(commentService.updateCommentById(postId,commentDto, commentId),HttpStatus.OK);
    }

    @ApiOperation(value = "Delete comment by id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public  ResponseEntity<String>  deleteCommentById(@PathVariable(value = "postId") Long postId, @PathVariable(value = "id") Long commentId){
        commentService.deleteCommentById(postId,commentId);
        return  new ResponseEntity<>("Comment is successfully deleted of post id : "+ postId,HttpStatus.OK);
    }
}
