package com.example.socialnetwork.service.impl;

import com.example.socialnetwork.domain.dto.PostDto;
import com.example.socialnetwork.domain.model.Post;
import com.example.socialnetwork.domain.model.User;
import com.example.socialnetwork.mapper.PostMapper;
import com.example.socialnetwork.repository.PostRepository;
import com.example.socialnetwork.repository.UserRepository;
import com.example.socialnetwork.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @Override
    public PostDto getPostById(Long id) {
        return postRepository.findById(id).map(postMapper::toDto).orElse(null);
    }

    @Override
    public List<PostDto> getPostsByAuthor(String author) {
        return postRepository.findByUserUsername(author).stream()
                .map(postMapper::toDto)
                .toList();
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::toDto)
                .toList();
    }

    @Override
    public List<PostDto> getNAllPosts() {
        return postRepository.findNAll().stream()
                .map(postMapper::toDto)
                .toList();
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = postMapper.toEntity(postDto);
        User user = userRepository.findByUsername(postDto.getAuthor())
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUsername(postDto.getAuthor());
                    newUser.setCreatedAt(LocalDateTime.now());
                    return userRepository.save(newUser);
                });
        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());
        Post saved = postRepository.save(post);
        return postMapper.toDto(saved);
    }
}