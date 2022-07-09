package com.sboot.blog.repository;

import com.sboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;

// Repository -> CrudRepository -> PagingAndSortingRepository -> JpaRepository
// no need to annotated this interface as @Repository => SimpleJpaRepository is class internally using @Repository
// annotation so no need to write here.
public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("SELECT p FROM Post p WHERE " +
            "p.title LIKE CONCAT('%', :query, '%')"+
            "Or p.description LIKE CONCAT('%' , :query, '%')")
    List<Post> searchPost(String query);
}
