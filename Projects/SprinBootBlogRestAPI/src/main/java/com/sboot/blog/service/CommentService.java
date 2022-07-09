package com.sboot.blog.service;

import com.sboot.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(long postId, long commentId);

    CommentDto updateCommentById(long postId, CommentDto commentDto, long commentId);

    void deleteCommentById(long postId, long commentId);
}
