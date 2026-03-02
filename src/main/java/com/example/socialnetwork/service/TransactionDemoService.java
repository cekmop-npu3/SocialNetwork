package com.example.socialnetwork.service;

import com.example.socialnetwork.domain.dto.PostDto;
import com.example.socialnetwork.domain.model.Post;
import com.example.socialnetwork.domain.model.User;
import com.example.socialnetwork.mapper.PostMapper;
import com.example.socialnetwork.repository.CommentRepository;
import com.example.socialnetwork.repository.PostRepository;
import com.example.socialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionDemoService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostMapper postMapper;

    public void createPostAndCommentWithoutTransaction(PostDto postDto) {
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
        postRepository.save(post);

        throw new RuntimeException("Ошибка после сохранения поста");
    }

    @Transactional
    public void createPostAndCommentWithTransaction(PostDto postDto) {
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
        postRepository.save(post);

        throw new RuntimeException("Ошибка после сохранения поста");
    }
}