package com.example.socialnetwork.controller.post;

import com.example.socialnetwork.domain.dto.PostDto;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/posts")
public interface PostAPI {
    @GetMapping("/{id}")
    PostDto getPostById(@PathVariable("id") Long id);

    @GetMapping
    List<PostDto> getPostsByAuthor(@RequestParam(value = "author", required = false) String author);

    @PostMapping
    PostDto createPost(@RequestBody PostDto postDto);

    @PutMapping("/{id}")
    PostDto updatePost(@PathVariable("id") Long id, @RequestBody PostDto postDto);

    @DeleteMapping("/{id}")
    void deletePost(@PathVariable("id") Long id);
}
