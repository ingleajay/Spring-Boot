package com.sboot.blog.service.impl;

import com.sboot.blog.entity.Post;
import com.sboot.blog.entity.User;
import com.sboot.blog.exceptions.ResourceNotFoundException;
import com.sboot.blog.payload.PostDto;
import com.sboot.blog.payload.PostResponse;
import com.sboot.blog.repository.PostRepository;
import com.sboot.blog.repository.UserRepository;
import com.sboot.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

// it tells that class is service class
@Service
public class PostServiceImpl implements PostService {

    // inject postRepository through constructor injection
    // if we have class with only one constructor then no need of @Autowired on constructor we can omit.

    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    private PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto, Long userId) {

        // we have to send response as DTO not entity so first convert DTO - entity then entity - DTO as response

        // Convert DTO to entity
        Post post = mapDTOToEntity(postDto);
        // save post into db
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        post.setDateCreated(new Date());
        post.setDateUpdated(new Date());
        post.setUsers(user);
        Post newPost = postRepository.save(post);
        // convert entity to DTO
        PostDto postDtoResponse = mapEntityToDTO(newPost);
        return postDtoResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                    Sort.by(sortBy).descending();

        // create pagable instance
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);

        // get content from page object
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> contents= listOfPosts.stream().map(post ->mapEntityToDTO(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(contents);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        // convert entity to DTO
        PostDto postDtoResponse = mapEntityToDTO(post);
        return postDtoResponse;
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id, Long userId) {

        // search valid user
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));


        // find post by id
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));

        // convert this post into postDto but before it save value
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setUsers(user);
        post.setDateUpdated(new Date());
        Post updatepost = postRepository.save(post);
        return mapEntityToDTO(updatepost);
    }

    @Override
    public void deletePostById(long id,Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        if(userId != null)
          postRepository.delete(post);
    }

    @Override
    public List<PostDto> searchPost(String query) {
        List<Post> post=  postRepository.searchPost(query);
        List<PostDto> searchPost= post.stream().map(p ->mapEntityToDTO(p)).collect(Collectors.toList());
        return searchPost;
    }

    // Convert DTO to entity
    private Post mapDTOToEntity(PostDto postDto){
        // by manually
//        Post post = new Post();
//        post.setId(postDto.getId());
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());

        // by model mapper
        Post post = modelMapper.map(postDto,Post.class);
        return  post;
    }

    // converts entity to DTO
    private PostDto mapEntityToDTO(Post newPost){
        // by manally
//        PostDto postDtoResponse = new PostDto();
//        postDtoResponse.setId(newPost.getId());
//        postDtoResponse.setTitle(newPost.getTitle());
//        postDtoResponse.setDescription(newPost.getDescription());
//        postDtoResponse.setContent(newPost.getContent());

        // by model mapper
        PostDto postDto = modelMapper.map(newPost, PostDto.class);
        return postDto;
    }
}
