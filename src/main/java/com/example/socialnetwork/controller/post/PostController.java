package com.example.socialnetwork.controller.post;

import com.example.socialnetwork.domain.dto.PostDto;
import com.example.socialnetwork.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController implements PostAPI {
    private final PostService postService;

    @Override
    public PostDto getPostById(Long id) {
        return postService.getPostById(id);
    }

    @Override
    public List<PostDto> getPostsByAuthor(String author) {
        if (author == null || author.isBlank()) {
            return postService.getAllPosts();
        }
        return postService.getPostsByAuthor(author);}

    @Override
    public PostDto createPost(PostDto postDto) {
        return postService.createPost(postDto);
    }
}
