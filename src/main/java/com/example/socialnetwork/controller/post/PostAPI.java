package com.example.socialnetwork.controller.post;

import com.example.socialnetwork.domain.dto.PostDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RequestMapping("/api/posts")
public interface PostAPI {
    @GetMapping("/{id}")
    PostDto getPostById(@PathVariable("id") Long id);

    @GetMapping
    List<PostDto> getPostsByAuthor(@RequestParam(value = "author", required = false) String author);

    @PostMapping
    PostDto createPost(@RequestBody PostDto postDto);
}
