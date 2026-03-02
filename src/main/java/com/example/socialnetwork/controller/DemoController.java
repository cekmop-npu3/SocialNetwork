package com.example.socialnetwork.controller;

import com.example.socialnetwork.domain.model.Post;
import com.example.socialnetwork.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {
    private final PostRepository postRepository;

    @GetMapping("/nplus1/posts")
    public List<Post> getPostsNPlus1() {
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            post.getComments().size();
        }
        return posts;
    }

    @GetMapping("/solved/posts")
    public List<Post> getPostsSolved() {
        return postRepository.findAll();
    }
}