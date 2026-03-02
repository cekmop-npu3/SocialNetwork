package com.example.socialnetwork.repository;

import com.example.socialnetwork.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}