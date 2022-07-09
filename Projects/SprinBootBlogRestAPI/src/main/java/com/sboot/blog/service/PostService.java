package com.sboot.blog.service;

import com.sboot.blog.entity.User;
import com.sboot.blog.payload.PostDto;
import com.sboot.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Long userId);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir);
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto,long id,Long userId);
    void deletePostById(long id,Long userId);

    List<PostDto> searchPost(String query);
}
