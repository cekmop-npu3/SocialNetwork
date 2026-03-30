package com.example.socialnetwork.service;

import com.example.socialnetwork.domain.dto.PostDto;
import java.util.List;

public interface PostService {
    PostDto getPostById(Long id);

    List<PostDto> getPostsByAuthor(String author);

    List<PostDto> getAllPosts();

    List<PostDto> getAllPostsNPlusOne();

    List<PostDto> getAllPostsEntityGraph();

    List<PostDto> getAllPostsFetchJoin();

    PostDto createPost(PostDto postDto);

    PostDto updatePost(Long id, PostDto postDto);

    void deletePost(Long id);
}
