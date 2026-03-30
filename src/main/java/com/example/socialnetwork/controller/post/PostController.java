package com.example.socialnetwork.controller.post;

import com.example.socialnetwork.domain.dto.PostDto;
import com.example.socialnetwork.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return postService.getPostsByAuthor(author);
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        return postService.createPost(postDto);
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {
        return postService.updatePost(id, postDto);
    }

    @Override
    public void deletePost(Long id) {
        postService.deletePost(id);
    }

    @GetMapping("/demo/nplus1")
    public List<PostDto> getPostsNPlusOne() {
        return postService.getAllPostsNPlusOne();
    }

    @GetMapping("/demo/entity-graph")
    public List<PostDto> getPostsEntityGraph() {
        return postService.getAllPostsEntityGraph();
    }

    @GetMapping("/demo/fetch-join")
    public List<PostDto> getPostsFetchJoin() {
        return postService.getAllPostsFetchJoin();
    }
}
