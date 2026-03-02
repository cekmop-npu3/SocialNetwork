package com.example.socialnetwork.controller;

import com.example.socialnetwork.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.socialnetwork.domain.dto.PostDto;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {
    private final PostService postService;

    @GetMapping("/nplus1/posts")
    public List<PostDto> getPostsNPlus1() {
        return postService.getNAllPosts();
    }

    @GetMapping("/solved/posts")
    public List<PostDto> getPostsSolved() {
        return postService.getAllPosts();
    }
}