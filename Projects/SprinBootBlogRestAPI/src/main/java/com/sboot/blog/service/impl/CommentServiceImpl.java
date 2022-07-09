package com.sboot.blog.service.impl;

import com.sboot.blog.entity.Comment;
import com.sboot.blog.entity.Post;
import com.sboot.blog.exceptions.BlogAPIException;
import com.sboot.blog.exceptions.ResourceNotFoundException;
import com.sboot.blog.payload.CommentDto;
import com.sboot.blog.repository.CommentRepository;
import com.sboot.blog.repository.PostRepository;
import com.sboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository , PostRepository postRepository) {
        this.commentRepository= commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapDtoToEntity(commentDto);

        // retrive post enity by id;
        Post post = postRepository.findById(postId).orElseThrow(()->new
                ResourceNotFoundException("Post","id",postId));

        // set post to comment entity
        comment.setPost(post);

        // comment entity to db
        Comment newComment = commentRepository.save(comment);
        return mapEntityToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        // convert list of comments to DTo
        return comments.stream().map(comment -> mapEntityToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
       Comment comment =  commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        return mapEntityToDto(comment);
    }

    @Override
    public CommentDto updateCommentById(long postId, CommentDto commentDto, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        Comment comment =  commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setPost(post);
        Comment updateComment = commentRepository.save(comment);
        return mapEntityToDto(updateComment);
    }

    @Override
    public void deleteCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        Comment comment =  commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        commentRepository.delete(comment);
    }

    // convert entity to DTO
    private CommentDto mapEntityToDto(Comment comment){
        // by manually
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());

        // by maodel mapper
        CommentDto commentDto = modelMapper.map(comment,CommentDto.class);
        return commentDto;
    }

    // convert Dto to entity
    private Comment mapDtoToEntity(CommentDto commentDto){
        // by manually
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());

        // by model mapper
        Comment comment = modelMapper.map(commentDto,Comment.class);
        return  comment;
    }
}
