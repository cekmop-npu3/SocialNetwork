package com.example.socialnetwork.config;

import com.example.socialnetwork.domain.dto.PostDto;
import com.example.socialnetwork.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DataLoader {
    private final PostService postService;

    @PostConstruct
    public void loadData() {
        postService.createPost(new PostDto(null, "Hello world!", "john", null));
        postService.createPost(new PostDto(null, "Second post", "jane", null));
        postService.createPost(new PostDto(null, "Spring Boot is awesome", "john", null));
    }
}
