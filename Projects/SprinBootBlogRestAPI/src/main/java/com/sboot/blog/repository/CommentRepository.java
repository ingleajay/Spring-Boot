package com.sboot.blog.repository;

import com.sboot.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    // find particlur post id for comment
    List<Comment> findByPostId(long id);
}
