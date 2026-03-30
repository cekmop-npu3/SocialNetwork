package com.example.socialnetwork.repository;

import com.example.socialnetwork.domain.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserUsername(String username);

    @Query("select p from Post p")
    List<Post> findAllForNPlusOne();

    @EntityGraph(attributePaths = {"user", "comments", "tags"})
    @Query("select p from Post p")
    List<Post> findAllWithEntityGraph();

    @Query("select distinct p from Post p "
            + "left join fetch p.user "
            + "left join fetch p.comments "
            + "left join fetch p.tags") 
    List<Post> findAllWithFetchJoin();
}
