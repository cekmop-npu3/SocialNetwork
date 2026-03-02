package com.example.socialnetwork.service;

import com.example.socialnetwork.domain.dto.PostDto;
import java.util.List;

public interface PostService {
    PostDto getPostById(Long id);
    List<PostDto> getPostsByAuthor(String author);
    List<PostDto> getAllPosts();
    List<PostDto> getNAllPosts();
    PostDto createPost(PostDto postDto);
}
