package com.example.socialnetwork.config;

import com.example.socialnetwork.domain.dto.PostDto;
import com.example.socialnetwork.service.PostService;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader {
    private final PostService postService;

    @PostConstruct
    public void loadData() {
        postService.createPost(new PostDto(null, "Hello world!", "john", null, null, List.of("intro")));
        postService.createPost(new PostDto(null, "Second post", "jane", null, null, List.of("daily", "spring")));
        postService.createPost(new PostDto(null, "Spring Boot is awesome", "john", null, null, List.of("spring")));
    }
}
