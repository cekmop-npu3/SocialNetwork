package com.example.socialnetwork.repository;

import com.example.socialnetwork.domain.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}