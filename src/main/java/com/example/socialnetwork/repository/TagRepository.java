package com.example.socialnetwork.repository;

import com.example.socialnetwork.domain.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}