package com.example.socialnetwork.repository;

import com.example.socialnetwork.domain.model.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserUsername(String username);

    @EntityGraph(attributePaths = {"comments"})
    @Override
    List<Post> findAll();

    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.comments WHERE p.id = :id")
    Post findByIdWithComments(@Param("id") Long id);
}