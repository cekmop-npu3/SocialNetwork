package com.example.socialnetwork.service.impl;

import com.example.socialnetwork.domain.dto.PostDto;
import com.example.socialnetwork.domain.model.Post;
import com.example.socialnetwork.mapper.PostMapper;
import com.example.socialnetwork.repository.PostRepository;
import com.example.socialnetwork.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id);
        return postMapper.toDto(post);
    }

    @Override
    public List<PostDto> getPostsByAuthor(String author) {
        return postRepository.findByAuthor(author).stream().map(postMapper::toDto).toList();
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::toDto)
                .toList();
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = postMapper.toEntity(postDto);
        Post saved = postRepository.save(post);
        return postMapper.toDto(saved);
    }
}
